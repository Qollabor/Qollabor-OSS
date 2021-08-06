package org.qollabor.processtask.akka.command.response;

import org.qollabor.akka.actor.command.response.ModelResponse;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.akka.command.ProcessCommand;

@Manifest
public class ProcessResponse extends ModelResponse {
    public ProcessResponse(ProcessCommand command) {
        super(command);
    }

    public ProcessResponse(ValueMap json) {
        super(json);
    }
}
