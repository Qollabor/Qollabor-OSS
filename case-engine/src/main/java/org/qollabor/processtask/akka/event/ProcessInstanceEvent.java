package org.qollabor.processtask.akka.event;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.event.BaseModelEvent;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.instance.ProcessTaskActor;

import java.io.IOException;

public abstract class ProcessInstanceEvent extends BaseModelEvent<ProcessTaskActor> {
    public static final String TAG = "qollabor:process";

    protected ProcessInstanceEvent(ProcessTaskActor processInstance) {
        super(processInstance);
    }

    protected ProcessInstanceEvent(ValueMap json) {
        super(json);
    }

    @Override
    public void updateState(ProcessTaskActor actor) {
        // Nothing to update here. (as of now)
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.writeModelEvent(generator);
    }
}
