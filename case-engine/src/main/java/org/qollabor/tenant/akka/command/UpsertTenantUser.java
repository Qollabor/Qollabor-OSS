package org.qollabor.tenant.akka.command;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.command.exception.InvalidCommandException;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.akka.command.response.TenantResponse;

import java.io.IOException;

@Manifest
public class UpsertTenantUser extends TenantCommand {
    private final TenantUserInformation newUser;

    public UpsertTenantUser(TenantUser tenantOwner, TenantUserInformation newUser) {
        super(tenantOwner);
        this.newUser = newUser;
    }

    public UpsertTenantUser(ValueMap json) {
        super(json);
        this.newUser = TenantUserInformation.from(json.with(Fields.newTenantUser));
    }

    @Override
    public void validate(TenantActor tenant) throws InvalidCommandException {
        super.validate(tenant);
        validateNotLastOwner(tenant, newUser);
    }

    @Override
    public TenantResponse process(TenantActor tenant) {
        tenant.upsertUser(newUser);
        return new TenantResponse(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.newTenantUser, newUser.toValue());
    }
}