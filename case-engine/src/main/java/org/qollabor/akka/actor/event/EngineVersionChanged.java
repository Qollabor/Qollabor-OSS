package org.qollabor.akka.actor.event;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.QollaborVersion;
import org.qollabor.akka.actor.ModelActor;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;

import java.io.IOException;

@Manifest
public class EngineVersionChanged extends BaseModelEvent {

    private final QollaborVersion version;

    public EngineVersionChanged(ModelActor actor, QollaborVersion version) {
        super(actor);
        this.version = version;
    }

    public EngineVersionChanged(ValueMap json) {
        super(json);
        this.version = new QollaborVersion(readMap(json, Fields.version));
    }

    @Override
    public void updateState(ModelActor actor) {
        actor.setEngineVersion(this.version);
    }

    /**
     * Returns the version of the engine that is currently applied in the case
     * @return
     */
    public QollaborVersion version() {
        return version;
    }

    @Override
    public String toString() {
        return "Engine version changed to " + version;
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.writeModelEvent(generator);
        super.writeField(generator, Fields.version, version.json());
    }
}
