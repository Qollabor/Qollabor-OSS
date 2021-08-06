package org.qollabor.processtask.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.instance.ProcessTaskActor;

@Manifest
public class ProcessTerminated extends ProcessEnded {
    public ProcessTerminated(ProcessTaskActor actor) {
        super(actor, new ValueMap());
    }

    public ProcessTerminated(ValueMap json) {
        super(json);
    }
}
