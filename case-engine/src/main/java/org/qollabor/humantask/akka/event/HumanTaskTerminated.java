/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.humantask.akka.event;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.humantask.instance.TaskAction;
import org.qollabor.humantask.instance.TaskState;

import java.io.IOException;

@Manifest
public class HumanTaskTerminated extends HumanTaskTransitioned {
    public HumanTaskTerminated(HumanTask task) {
        super(task, TaskState.Terminated, TaskAction.Terminate);
    }

    public HumanTaskTerminated(ValueMap json) {
        super(json);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.writeTransitionEvent(generator);
    }
}
