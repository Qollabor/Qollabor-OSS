/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.definition.sentry;

import org.qollabor.cmmn.definition.CMMNElementDefinition;
import org.qollabor.cmmn.definition.ConstraintDefinition;
import org.qollabor.cmmn.definition.ModelDefinition;
import org.qollabor.cmmn.instance.sentry.Criterion;
import org.w3c.dom.Element;

public class IfPartDefinition extends ConstraintDefinition {
    public IfPartDefinition(Element element, ModelDefinition modelDefinition, CMMNElementDefinition parentElement) {
        super(element, modelDefinition, parentElement);
    }

    IfPartDefinition(ModelDefinition modelDefinition, CMMNElementDefinition parentElement) {
        super(modelDefinition, parentElement, true); // Default ifPart: evaluates always to true
    }

    public boolean evaluate(Criterion criterion) {
        return getExpressionDefinition().getEvaluator().evaluateIfPart(criterion, this);
    }
}
