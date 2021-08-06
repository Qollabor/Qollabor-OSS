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
public class DelegateTask extends WorkflowCommand {
    private final String assignee;

    public DelegateTask(TenantUser tenantUser, String caseInstanceId, String taskId, TenantUser delegatee) {
        super(tenantUser, caseInstanceId, taskId);
        this.assignee = delegatee.id();
    }

    public DelegateTask(ValueMap json) {
        super(json);
        this.assignee = readField(json, Fields.assignee);
    }

    @Override
    public void validate(HumanTask task) {
        super.validateTaskOwnership(task);
        super.validateState(task, TaskState.Assigned);
        super.validateCaseTeamMembership(task, assignee);
    }

    @Override
    public HumanTaskResponse process(WorkflowTask workflowTask) {
        workflowTask.delegate(assignee);
        return new HumanTaskResponse(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.assignee, assignee);
    }
}
