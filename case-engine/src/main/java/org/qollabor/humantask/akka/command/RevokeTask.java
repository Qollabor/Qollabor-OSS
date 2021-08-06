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
public class RevokeTask extends WorkflowCommand {
    public RevokeTask(TenantUser tenantUser, String caseInstanceId, String taskId) {
        super(tenantUser, caseInstanceId, taskId);
    }

    public RevokeTask(ValueMap json) {
        super(json);
    }

    @Override
    public void validate(HumanTask task) {
        super.validateTaskOwnership(task);
        super.validateState(task, TaskState.Assigned, TaskState.Delegated);
    }

    @Override
    public HumanTaskResponse process(WorkflowTask workflowTask) {
        workflowTask.revoke();
        return new HumanTaskResponse(this);
    }
}
