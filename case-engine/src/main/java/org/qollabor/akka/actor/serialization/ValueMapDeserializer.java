package org.qollabor.akka.actor.serialization;

import org.qollabor.akka.actor.serialization.json.ValueMap;

/**
 * Deserializes a {@link ValueMap} based json AST into an object
 */
@FunctionalInterface
public interface ValueMapDeserializer<T extends QollaborSerializable> {
    T deserialize(ValueMap json);
}
