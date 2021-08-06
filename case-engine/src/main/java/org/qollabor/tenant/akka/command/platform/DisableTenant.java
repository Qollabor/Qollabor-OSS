package org.qollabor.tenant.akka.command.platform;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.identity.PlatformUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.akka.command.response.TenantResponse;

import java.io.IOException;

@Manifest
public class DisableTenant extends PlatformTenantCommand {
    public DisableTenant(PlatformUser user, String tenantId) {
        super(user, tenantId);
    }

    public DisableTenant(ValueMap json) {
        super(json);
    }

    @Override
    public TenantResponse process(TenantActor tenant) {
        tenant.disable();
        return new TenantResponse(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
    }
}

