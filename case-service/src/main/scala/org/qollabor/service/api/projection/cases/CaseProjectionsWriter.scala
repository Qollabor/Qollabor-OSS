package org.qollabor.service.api.projection.cases

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import org.qollabor.cmmn.akka.event.CaseEvent
import org.qollabor.infrastructure.cqrs.{OffsetStorage, OffsetStorageProvider}
import org.qollabor.service.api.cases.CaseReader
import org.qollabor.service.api.projection.slick.SlickEventMaterializer
import org.qollabor.service.api.projection.{LastModifiedRegistration, RecordsPersistence}

class CaseProjectionsWriter(persistence: RecordsPersistence, offsetStorageProvider: OffsetStorageProvider)(implicit override val system: ActorSystem) extends SlickEventMaterializer[CaseEvent, CaseTransaction] with LazyLogging {

  import scala.concurrent.ExecutionContext.Implicits.global

  override val offsetStorage: OffsetStorage = offsetStorageProvider.storage("CaseProjectionsWriter")
  override val tag: String = CaseEvent.TAG
  override val lastModifiedRegistration: LastModifiedRegistration = CaseReader.lastModifiedRegistration

  def createTransaction(caseInstanceId: String, tenant: String) = new CaseTransaction(caseInstanceId, tenant, persistence)

}
