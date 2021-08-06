/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.instance.parameter;

import org.qollabor.cmmn.definition.parameter.OutputParameterDefinition;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.cmmn.instance.Parameter;
import org.qollabor.akka.actor.serialization.json.Value;

/**
 * CaseOutputParameters are bound to the case file. They are filled at the moment the CasePlan completes.
 */
public class CaseOutputParameter extends Parameter<OutputParameterDefinition> {
    public CaseOutputParameter(OutputParameterDefinition definition, Case caseInstance) {
        super(definition, caseInstance, null);
    }

    @Override
    public Value<?> getValue() {
        super.bindCaseFileToParameter();
        return super.getValue();
    }
}
