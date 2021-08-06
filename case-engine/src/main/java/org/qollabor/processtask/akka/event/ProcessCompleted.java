package org.qollabor.processtask.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.instance.ProcessTaskActor;

@Manifest
public class ProcessCompleted extends ProcessEnded {
    public ProcessCompleted(ProcessTaskActor actor, ValueMap outputParameters) {
        super(actor, outputParameters);
    }

    public ProcessCompleted(ValueMap json) {
        super(json);
    }
}
