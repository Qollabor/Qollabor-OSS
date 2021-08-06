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
import org.qollabor.cmmn.instance.Case;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.humantask.instance.TaskAction;
import org.qollabor.humantask.instance.TaskState;

import java.io.IOException;

@Manifest
public class HumanTaskAssigned extends HumanTaskTransitioned {
    /**
     * New assignee of the task
     */
    public final String assignee; // assignee of the task

    public HumanTaskAssigned(HumanTask task, String assignee) {
        this(task, assignee, TaskState.Assigned, TaskAction.Assign);
    }

    protected HumanTaskAssigned(HumanTask task, String assignee, TaskState nextState, TaskAction transition) {
        super(task, nextState, transition);
        this.assignee = assignee;
    }

    public HumanTaskAssigned(ValueMap json) {
        super(json);
        this.assignee = readField(json, Fields.assignee);
    }

    @Override
    public void updateState(Case caseInstance) {
        super.updateState(caseInstance);
        getTask().getImplementation().updateState(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.writeTransitionEvent(generator);
        writeField(generator, Fields.assignee, assignee);
    }
}
