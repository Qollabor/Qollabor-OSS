/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.humantask.akka.command;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.humantask.akka.command.response.HumanTaskResponse;
import org.qollabor.humantask.instance.TaskState;
import org.qollabor.humantask.instance.WorkflowTask;

import java.io.IOException;

@Manifest
public class AssignTask extends WorkflowCommand {
    private final String assignee;

    public AssignTask(TenantUser tenantUser, String caseInstanceId, String taskId, TenantUser assignee) {
        super(tenantUser, caseInstanceId, taskId);
        this.assignee = assignee.id();
    }

    public AssignTask(ValueMap json) {
        super(json);
        this.assignee = readField(json, Fields.assignee);
    }

    @Override
    public void validate(HumanTask task) {
        super.validateCaseOwnership(task);
        TaskState currentTaskState = task.getImplementation().getCurrentState();
        if (! currentTaskState.isActive()) {
            raiseException("Cannot be done because the task is in " + currentTaskState + " state, but must be in an active state (Unassigned or Assigned)");
        }
        super.validateCaseTeamMembership(task, assignee);
    }

    @Override
    public HumanTaskResponse process(WorkflowTask workflowTask) {
        workflowTask.assign(this.assignee);
        return new HumanTaskResponse(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.assignee, assignee);
    }
}
