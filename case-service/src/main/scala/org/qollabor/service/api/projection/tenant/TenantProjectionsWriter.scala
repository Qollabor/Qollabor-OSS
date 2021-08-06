package org.qollabor.service.api.projection.tenant

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.qollabor.identity.IdentityProvider
import org.qollabor.infrastructure.cqrs.{OffsetStorage, OffsetStorageProvider}
import org.qollabor.service.api.projection.query.UserQueries
import org.qollabor.service.api.projection.slick.SlickEventMaterializer
import org.qollabor.service.api.projection.{LastModifiedRegistration, RecordsPersistence}
import org.qollabor.service.api.tenant.TenantReader
import org.qollabor.tenant.akka.event.TenantEvent

class TenantProjectionsWriter
  (userQueries: UserQueries, updater: RecordsPersistence, offsetStorageProvider: OffsetStorageProvider)
  (implicit val system: ActorSystem, implicit val userCache: IdentityProvider) extends SlickEventMaterializer[TenantEvent, TenantTransaction] with LazyLogging {

  import scala.concurrent.ExecutionContext.Implicits.global

  override val offsetStorage: OffsetStorage = offsetStorageProvider.storage("TenantProjectionsWriter")
  override val tag: String = TenantEvent.TAG

  override def createTransaction(actorId: String, tenant: String): TenantTransaction = new TenantTransaction(actorId, userQueries, updater, userCache)

  override val lastModifiedRegistration: LastModifiedRegistration = TenantReader.lastModifiedRegistration
}
