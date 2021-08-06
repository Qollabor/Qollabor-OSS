/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.akka.command;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.akka.command.response.CaseResponse;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.cmmn.instance.PlanItem;
import org.qollabor.cmmn.instance.Transition;
import org.qollabor.akka.actor.serialization.json.ValueMap;

import java.io.IOException;

@Manifest
public class MakeCaseTransition extends CaseCommand {
    private final Transition transition;

    /**
     * Triggers the specified transition on the case (effectively on the case plan).
     *
     * @param caseInstanceId The id of the case in which to perform this command.
     * @param transition     The transition to be executed on the case
     */
    public MakeCaseTransition(TenantUser tenantUser, String caseInstanceId, Transition transition) {
        super(tenantUser, caseInstanceId);
        this.transition = transition;
    }

    public MakeCaseTransition(ValueMap json) {
        super(json);
        this.transition = readEnum(json, Fields.transition, Transition.class);
    }

    public Transition getTransition() {
        return transition;
    }

    @Override
    public String toString() {
        return "Transition Case." + transition;
    }

    @Override
    public CaseResponse process(Case caseInstance) {
        PlanItem casePlan = caseInstance.getCasePlan();
        caseInstance.makePlanItemTransition(casePlan, transition);
        return new CaseResponse(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.transition, transition);
    }
}
