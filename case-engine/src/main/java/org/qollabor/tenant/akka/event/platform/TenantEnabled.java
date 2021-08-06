package org.qollabor.tenant.akka.event.platform;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;

@Manifest
public class TenantEnabled extends PlatformEvent {

    public TenantEnabled(TenantActor tenant) {
        super(tenant);
    }

    public TenantEnabled(ValueMap json) {
        super(json);
    }

    @Override
    public void updateState(TenantActor tenant) {
        tenant.updateState(this);
    }
}
