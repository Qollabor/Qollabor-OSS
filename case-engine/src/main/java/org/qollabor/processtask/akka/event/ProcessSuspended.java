package org.qollabor.processtask.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.instance.ProcessTaskActor;

@Manifest
public class ProcessSuspended extends ProcessInstanceEvent {
    public ProcessSuspended(ProcessTaskActor actor) {
        super(actor);
    }

    public ProcessSuspended(ValueMap json) {
        super(json);
    }
}
