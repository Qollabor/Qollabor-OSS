package org.qollabor.akka.actor.serialization;

import org.qollabor.timerservice.TimerStorage;

public class SnapshotSerializer extends QollaborSerializer {
    static void register() {
        registerSnapshots();
    }

    private static void registerSnapshots() {
        addManifestWrapper(TimerStorage.class, TimerStorage::new);
    }
}
