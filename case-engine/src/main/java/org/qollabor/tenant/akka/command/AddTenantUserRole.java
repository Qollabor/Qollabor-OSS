package org.qollabor.tenant.akka.command;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.User;

@Manifest
public class AddTenantUserRole extends RoleCommand {

    public AddTenantUserRole(TenantUser tenantOwner, String userId, String role) {
        super(tenantOwner, userId, role);
    }

    public AddTenantUserRole(ValueMap json) {
        super(json);
    }

    @Override
    protected void updateUser(User user) {
        user.addRole(role);
    }
}