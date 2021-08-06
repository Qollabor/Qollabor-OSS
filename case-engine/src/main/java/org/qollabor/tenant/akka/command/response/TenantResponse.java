package org.qollabor.tenant.akka.command.response;

import org.qollabor.akka.actor.command.response.ModelResponse;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.akka.command.TenantCommand;

@Manifest
public class TenantResponse extends ModelResponse {
    public TenantResponse(TenantCommand command) {
        super(command);
    }

    public TenantResponse(ValueMap json) {
        super(json);
    }
}
