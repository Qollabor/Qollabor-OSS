package org.qollabor.akka.actor.router

import akka.actor.{ActorRef, Props}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings, ShardRegion}
import org.qollabor.akka.actor.CaseSystem
import org.qollabor.akka.actor.command.ModelCommand
import org.qollabor.cmmn.akka.command.CaseCommand
import org.qollabor.cmmn.instance.Case
import org.qollabor.processtask.akka.command.ProcessCommand
import org.qollabor.processtask.instance.ProcessTaskActor
import org.qollabor.tenant.TenantActor
import org.qollabor.tenant.akka.command.TenantCommand
import org.qollabor.timerservice.akka.command.TimerServiceCommand

/**
  * Clustered representation, router as singleton actor
  */
class ClusterRouter extends CaseMessageRouter {
  logger.info("Starting case system in cluster mode")

  private lazy val caseShardRouter: ActorRef = ClusterSharding(context.system).shardRegion(caseShardTypeName)
  private lazy val processShardRouter: ActorRef = ClusterSharding(context.system).shardRegion(processShardTypeName)
  private lazy val tenantShardRouter: ActorRef = ClusterSharding(context.system).shardRegion(tenantShardTypeName)


  final val caseShardTypeName: String = "case"
  final val processShardTypeName: String = "process"
  final val tenantShardTypeName: String = "tenant"

  val numberOfPartitions = 100
  val localSystemKey: Long = "localSystemKey".hashCode

  override def forwardMessage(m: ModelCommand[_]) = {
    // Forward message into the right shardregion
    val shardRouter: ActorRef = m match {
      case _: CaseCommand => caseShardRouter
      case _: ProcessCommand => processShardRouter
      case _: TenantCommand => tenantShardRouter
      case _: TimerServiceCommand => CaseSystem.timerService
    }
    shardRouter.forward(m)
  }

  /**
    * Create the shard system first before we can handle messages
    */
  override def preStart(): Unit = {

    // Note: we're creating 3 shards, one for each type of ModelActor currently known.
    //  This is needed because the classic sharding system that is used here does not
    //  support different/dynamic types of Props in the entityProps.
    //  Perhaps with the new akka 2.6 this can be solved in a new way currently not known to us.

    def startShard(typeName: String, clazz: Class[_]) = {
      ClusterSharding(context.system).start(typeName = typeName, entityProps = Props(clazz), settings = ClusterShardingSettings(context.system), extractEntityId = idExtractor, extractShardId = shardResolver)
    }

    // Start the shard system
    startShard(caseShardTypeName, classOf[Case])
    startShard(processShardTypeName, classOf[ProcessTaskActor])
    startShard(tenantShardTypeName, classOf[TenantActor])
  }

  private val idExtractor: ShardRegion.ExtractEntityId = {
    case pl: CaseCommand => (pl.actorId, pl)
    case pl: ProcessCommand => (pl.actorId, pl)
    case pl: TenantCommand => (pl.actorId, pl)
  }

  private val shardResolver: ShardRegion.ExtractShardId = msg ⇒ msg match {
    case m: ModelCommand[_] => {
      val pidHashKey: Long = m.actorId.hashCode()
      val shard = ((localSystemKey + pidHashKey) % numberOfPartitions).toString
      shard
    }
  }
}
