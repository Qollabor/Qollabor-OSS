package org.qollabor.tenant.akka.event;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.User;

import java.io.IOException;

@Manifest
public class TenantUserUpdated extends TenantUserEvent {
    public final String name;
    public final String email;

    public TenantUserUpdated(TenantActor tenant, String userId, String name, String email) {
        super(tenant, userId);
        this.name = name;
        this.email = email;
    }

    public TenantUserUpdated(ValueMap json) {
        super(json);
        this.name = readField(json, Fields.name);
        this.email = readField(json, Fields.email);
    }

    @Override
    protected void updateUserState(User user) {
        user.updateState(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.name, name);
        writeField(generator, Fields.email, email);
    }
}
