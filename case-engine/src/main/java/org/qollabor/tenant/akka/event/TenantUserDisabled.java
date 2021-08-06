package org.qollabor.tenant.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.User;

@Manifest
public class TenantUserDisabled extends TenantUserEvent {

    public TenantUserDisabled(TenantActor tenant, String userId) {
        super(tenant, userId);
    }

    public TenantUserDisabled(ValueMap json) {
        super(json);
    }

    @Override
    protected void updateUserState(User user) {
        user.updateState(this);
    }
}
