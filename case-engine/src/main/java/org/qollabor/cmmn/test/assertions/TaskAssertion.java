/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.test.assertions;

import org.qollabor.cmmn.akka.event.plan.PlanItemCreated;
import org.qollabor.cmmn.instance.PlanItem;
import org.qollabor.cmmn.instance.Task;
import org.qollabor.cmmn.instance.task.cmmn.CaseTask;
import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.cmmn.instance.task.process.ProcessTask;
import org.qollabor.cmmn.test.CaseTestCommand;

public class TaskAssertion extends PlanItemAssertion {

    TaskAssertion(CaseTestCommand command, PlanItemCreated planItem) {
        super(command, planItem);
        super.assertType(new Class[]{Task.class, HumanTask.class, ProcessTask.class, CaseTask.class});
    }

    @Override
    public <T extends PlanItem<?>> TaskAssertion assertType(Class<T> typeClass) {
        return (TaskAssertion) super.assertType(typeClass);
    }
}
