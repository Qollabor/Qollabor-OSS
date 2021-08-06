/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.humantask.akka.command;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.humantask.akka.command.response.HumanTaskResponse;
import org.qollabor.humantask.instance.TaskState;
import org.qollabor.humantask.instance.WorkflowTask;

@Manifest
public class ClaimTask extends WorkflowCommand {
    public ClaimTask(TenantUser tenantUser, String caseInstanceId, String taskId) {
        super(tenantUser, caseInstanceId, taskId);
    }

    public ClaimTask(ValueMap json) {
        super(json);
    }

    @Override
    public void validate(HumanTask task) {
        super.validateProperCaseRole(task);
        super.validateState(task, TaskState.Unassigned);
    }

    @Override
    public HumanTaskResponse process(WorkflowTask workflowTask) {
        workflowTask.claim(this.getUser().id());
        return new HumanTaskResponse(this);
    }
}
