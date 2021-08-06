package org.qollabor.tenant.akka.command;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.akka.command.response.TenantOwnersResponse;
import org.qollabor.tenant.akka.command.response.TenantResponse;
import org.qollabor.tenant.akka.event.TenantOwnersRequested;

import java.io.IOException;

@Manifest
public class GetTenantOwners extends TenantCommand {
    public GetTenantOwners(TenantUser tenantOwner) {
        super(tenantOwner);
    }

    public GetTenantOwners(ValueMap json) {
        super(json);
    }

    @Override
    public TenantResponse process(TenantActor tenant) {
        // We add this event to enable some form of audit logging
        tenant.addEvent(new TenantOwnersRequested(tenant));
        return new TenantOwnersResponse(this, tenant.getId(), tenant.getOwnerList());
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
    }
}

