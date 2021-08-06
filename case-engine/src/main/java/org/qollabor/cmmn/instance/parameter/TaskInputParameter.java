/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.instance.parameter;

import org.qollabor.cmmn.definition.parameter.InputParameterDefinition;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.cmmn.instance.Parameter;
import org.qollabor.cmmn.instance.Task;
import org.qollabor.akka.actor.serialization.json.Value;

/**
 * TaskInputParameter is specific from other parameters, in that its value is typically bound to the case file.
 * That is, if a {@link Task} assigns input parameters, the value of that parameter is typically retrieved from the case file.
 */
public class TaskInputParameter extends Parameter<InputParameterDefinition> {
    public TaskInputParameter(InputParameterDefinition definition, Case caseInstance) {
        super(definition, caseInstance, null);
    }

    @Override
    public Value<?> getValue() {
        super.bindCaseFileToParameter();
        return super.getValue();
    }
}
