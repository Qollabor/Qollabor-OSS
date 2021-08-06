package org.qollabor.processtask.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.instance.ProcessTaskActor;

@Manifest
public class ProcessFailed extends ProcessEnded {
    public ProcessFailed(ProcessTaskActor actor, ValueMap outputParameters) {
        super(actor, outputParameters);
    }

    public ProcessFailed(ValueMap json) {
        super(json);
    }
}
