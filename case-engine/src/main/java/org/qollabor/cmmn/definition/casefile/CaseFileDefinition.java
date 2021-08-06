/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.definition.casefile;

import org.qollabor.cmmn.definition.CMMNElementDefinition;
import org.qollabor.cmmn.definition.ModelDefinition;
import org.w3c.dom.Element;

public class CaseFileDefinition extends CaseFileItemCollectionDefinition {
    public CaseFileDefinition(Element element, ModelDefinition modelDefinition, CMMNElementDefinition parentElement) {
        super(element, modelDefinition, parentElement);
        if (element != null) {
            parse("caseFileItem", CaseFileItemDefinition.class, getChildren());
            if (getChildren().size() < 1) {
                modelDefinition.addDefinitionError("The case file must have at least one case file item");
            }
        }
    }
}
