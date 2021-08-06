package org.qollabor.akka.actor.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;

@Manifest
public class SentryEvent extends DebugEvent {
    public SentryEvent(ValueMap json) {
        super(json);
    }
}
