package org.qollabor.cmmn.instance.debug;

import org.qollabor.akka.actor.serialization.json.Value;

@FunctionalInterface
public interface DebugJsonAppender {
    Value info();
}
