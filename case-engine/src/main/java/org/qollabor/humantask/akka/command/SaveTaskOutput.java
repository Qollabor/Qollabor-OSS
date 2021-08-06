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
import org.qollabor.humantask.akka.command.response.HumanTaskResponse;
import org.qollabor.humantask.instance.WorkflowTask;

/**
 * Saves the output in the task. This output is not yet stored back in the case file, since that happens only when the task is completed.
 */
@Manifest
public class SaveTaskOutput extends TaskOutputCommand {
	public SaveTaskOutput(TenantUser tenantUser, String caseInstanceId, String taskId, ValueMap taskOutput) {
		super(tenantUser, caseInstanceId, taskId, taskOutput);
	}

	public SaveTaskOutput(ValueMap json) {
		super(json);
	}

	@Override
	public HumanTaskResponse process(WorkflowTask workflowTask) {
		workflowTask.saveOutput(this.taskOutput);
		return new HumanTaskResponse(this);
	}
}
