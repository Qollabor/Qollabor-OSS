/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.definition.sentry;

import org.qollabor.cmmn.definition.CMMNElementDefinition;
import org.qollabor.cmmn.definition.ModelDefinition;
import org.qollabor.cmmn.instance.sentry.Criterion;
import org.qollabor.cmmn.instance.sentry.OnPart;
import org.w3c.dom.Element;

public abstract class OnPartDefinition extends CMMNElementDefinition {
    public OnPartDefinition(Element element, ModelDefinition modelDefinition, CMMNElementDefinition parentElement) {
        super(element, modelDefinition, parentElement);
    }

    public abstract CMMNElementDefinition getSourceDefinition();

    public abstract OnPart<?, ?> createInstance(Criterion criterion);
}