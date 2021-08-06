/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.humantask.akka.event;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.humantask.instance.TaskAction;
import org.qollabor.humantask.instance.TaskState;

import java.io.IOException;

@Manifest
public class HumanTaskCompleted extends HumanTaskTransitioned {
    private final ValueMap taskOutput; // taskOutput - task saved output

    public HumanTaskCompleted(HumanTask task, ValueMap output) {
        super(task, TaskState.Completed, TaskAction.Complete);
        this.taskOutput = output;
    }

    public HumanTaskCompleted(ValueMap json) {
        super(json);
        this.taskOutput = readMap(json, Fields.taskOutput);
    }

    /**
     * Get assignee for the task
     * @return assignee for the task
     */
    public ValueMap getTaskOutput() {
        return this.taskOutput;
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.writeTransitionEvent(generator);
        writeField(generator, Fields.taskOutput, taskOutput);
    }
}
