package org.qollabor.tenant.akka.event.platform;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.QollaborVersion;
import org.qollabor.akka.actor.CaseSystem;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.tenant.TenantActor;

import java.io.IOException;

@Manifest
public class TenantCreated extends PlatformEvent {
    public final QollaborVersion engineVersion;

    public TenantCreated(TenantActor tenant) {
        super(tenant);
        this.engineVersion = CaseSystem.version();
    }

    public TenantCreated(ValueMap json) {
        super(json);
        this.engineVersion = new QollaborVersion(readMap(json, Fields.engineVersion));
    }

    @Override
    public void updateState(TenantActor tenant) {
        tenant.updateState(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.engineVersion, engineVersion.json());
    }
}
