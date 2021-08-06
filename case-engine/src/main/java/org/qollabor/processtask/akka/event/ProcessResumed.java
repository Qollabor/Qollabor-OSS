package org.qollabor.processtask.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.instance.ProcessTaskActor;

@Manifest
public class ProcessResumed extends ProcessInstanceEvent {
    public ProcessResumed(ProcessTaskActor actor) {
        super(actor);
    }

    public ProcessResumed(ValueMap json) {
        super(json);
    }
}
