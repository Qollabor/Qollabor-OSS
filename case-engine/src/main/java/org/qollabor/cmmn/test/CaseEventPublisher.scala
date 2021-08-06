package org.qollabor.cmmn.test

import akka.actor.ActorSystem
import akka.persistence.query.{EventEnvelope, Offset}
import akka.stream.scaladsl.{Sink, Source}
import akka.{Done, NotUsed}
import org.qollabor.akka.actor.event.ModelEvent
import org.qollabor.akka.actor.serialization.{DeserializationFailure, UnrecognizedManifest}
import org.qollabor.infrastructure.cqrs.ReadJournalProvider

import scala.concurrent.Future

class CaseEventPublisher(listener: CaseEventListener, implicit val system: ActorSystem) extends ReadJournalProvider{
  val source: Source[EventEnvelope, NotUsed] = journal.eventsByTag(ModelEvent.TAG, Offset.noOffset)
  source.mapAsync(1) {
    case EventEnvelope(newOffset, persistenceId, sequenceNr, evt: AnyRef) => {
      listener.handle(evt)
      Future.successful(Done)
    }
  }.runWith(Sink.ignore)
}
