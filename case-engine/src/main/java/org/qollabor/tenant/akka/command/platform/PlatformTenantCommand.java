package org.qollabor.tenant.akka.command.platform;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.CaseSystem;
import org.qollabor.akka.actor.command.exception.AuthorizationException;
import org.qollabor.akka.actor.command.exception.InvalidCommandException;
import org.qollabor.akka.actor.identity.PlatformUser;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;
import org.qollabor.tenant.akka.command.TenantCommand;

import java.io.IOException;

/**
 * PlatformTenantCommands can only be executed by platform owners
 */
@Manifest
public abstract class PlatformTenantCommand extends TenantCommand {
    protected PlatformTenantCommand(PlatformUser user, String tenantId) {
        super(TenantUser.fromPlatformOwner(user, tenantId));
    }

    protected PlatformTenantCommand(ValueMap json) {
        super(json);
    }

    @Override
    public void validate(TenantActor modelActor) throws InvalidCommandException {
        if (! CaseSystem.isPlatformOwner(getUser())) {
            throw new AuthorizationException("Only platform owners can invoke platform commands");
        }
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
    }
}

