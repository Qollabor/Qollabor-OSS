package org.qollabor.service.api.projection.slick

import akka.Done
import akka.persistence.query.Offset
import org.qollabor.akka.actor.event.{ModelEvent, TransactionEvent}

import scala.concurrent.Future

trait SlickTransaction[M <: ModelEvent[_]] {
  def handleEvent(event: M): Future[Done]

  def commit(offsetName: String, offset: Offset, transactionEvent: TransactionEvent[_]): Future[Done]
}
