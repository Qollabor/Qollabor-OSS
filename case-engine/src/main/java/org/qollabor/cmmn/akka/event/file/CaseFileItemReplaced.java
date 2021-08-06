/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.akka.event.file;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.Value;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.State;
import org.qollabor.cmmn.instance.casefile.CaseFileItem;
import org.qollabor.cmmn.instance.casefile.CaseFileItemTransition;

/**
 * Event caused by creation of a CaseFileItem
 */
@Manifest
public class CaseFileItemReplaced extends CaseFileEvent {
    public CaseFileItemReplaced(CaseFileItem item, Value<?> newValue) {
        super(item, State.Available, CaseFileItemTransition.Replace, newValue);
    }

    public CaseFileItemReplaced(ValueMap json) {
        super(json);
    }
}
