package org.qollabor.tenant.akka.command;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.User;

@Manifest
public class RemoveTenantUserRole extends RoleCommand {
    public RemoveTenantUserRole(TenantUser tenantOwner, String userId, String role) {
        super(tenantOwner, userId, role);
    }

    public RemoveTenantUserRole(ValueMap json) {
        super(json);
    }

    @Override
    protected void updateUser(User user) {
        user.removeRole(role);
    }
}