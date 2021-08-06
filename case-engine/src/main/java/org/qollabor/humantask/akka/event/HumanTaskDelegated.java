/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.humantask.akka.event;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.humantask.instance.TaskAction;
import org.qollabor.humantask.instance.TaskState;

@Manifest
public class HumanTaskDelegated extends HumanTaskAssigned {
    public HumanTaskDelegated(HumanTask task, String assignee) {
        super(task, assignee, TaskState.Delegated, TaskAction.Delegate);
    }

    public HumanTaskDelegated(ValueMap json) {
        super(json);
    }
}
