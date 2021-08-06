package org.qollabor.tenant.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;

@Manifest
public class TenantOwnersRequested extends TenantEvent {

    public TenantOwnersRequested(TenantActor tenant) {
        super(tenant);
    }

    public TenantOwnersRequested(ValueMap json) {
        super(json);
    }

    @Override
    public void updateState(TenantActor tenant) {
    }
}
