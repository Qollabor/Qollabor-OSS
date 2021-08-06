package org.qollabor.tenant.akka.command;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.command.exception.InvalidCommandException;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.User;

import java.io.IOException;

@Manifest
public class ReplaceTenantUser extends ExistingUserCommand {
    private final TenantUserInformation newUser;

    public ReplaceTenantUser(TenantUser tenantOwner, TenantUserInformation newUser) {
        super(tenantOwner, newUser.id());
        this.newUser = newUser;
    }

    public ReplaceTenantUser(ValueMap json) {
        super(json);
        this.newUser = TenantUserInformation.from(json.with(Fields.newTenantUser));
    }

    @Override
    public void validate(TenantActor tenant) throws InvalidCommandException {
        super.validate(tenant);
        validateNotLastOwner(tenant, newUser);
    }

    @Override
    protected void updateUser(User user) {
        user.replaceWith(newUser);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.newTenantUser, newUser.toValue());
    }
}