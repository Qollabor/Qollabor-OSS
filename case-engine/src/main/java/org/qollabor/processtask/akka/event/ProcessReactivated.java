package org.qollabor.processtask.akka.event;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.akka.command.ReactivateProcess;
import org.qollabor.processtask.instance.ProcessTaskActor;

import java.io.IOException;

@Manifest
public class ProcessReactivated extends ProcessInstanceEvent {
    public final ValueMap inputParameters;

    public ProcessReactivated(ProcessTaskActor actor, ReactivateProcess command) {
        super(actor);
        this.inputParameters = command.getInputParameters();
    }

    public ProcessReactivated(ValueMap json) {
        super(json);
        this.inputParameters = readMap(json, Fields.input);
    }

    @Override
    public void updateState(ProcessTaskActor actor) {
        actor.updateState(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.input, inputParameters);
    }}
