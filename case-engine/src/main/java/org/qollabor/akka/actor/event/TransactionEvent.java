package org.qollabor.akka.actor.event;

import org.qollabor.akka.actor.ModelActor;

import java.time.Instant;

public interface TransactionEvent<M extends ModelActor> extends ModelEvent<M> {
    Instant lastModified();
}
