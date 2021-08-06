/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.test.task;

import org.qollabor.cmmn.akka.command.StartCase;
import org.qollabor.cmmn.definition.CaseDefinition;
import org.qollabor.cmmn.instance.State;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.test.TestScript;
import org.qollabor.akka.actor.identity.TenantUser;
import org.junit.Test;

import java.util.Base64;

/**
 *
 *
 */
public class TestSMTPServer {
    private final CaseDefinition definitions = TestScript.getCaseDefinition("testdefinition/task/smtpcall.xml");
    private final TenantUser testUser = TestScript.getTestUser("Anonymous");


    @Test
    public void testSmtpProcess() {

        String caseInstanceId = "SMTP";
        TestScript testCase = new TestScript("SMTP");

        // Now start a case with a child being set within the JSON input
        ValueMap inputs = new ValueMap();
        ValueMap request = inputs.with("Request");
        request.putRaw("from", "Joop");
        request.putRaw("to", "Piet");
        request.putRaw("subject", "The engine is cool");
        request.putRaw("body", "Thank you for your contributions. You're still in office?");
        request.putRaw("replyTo", "Jan");

        String attachmentContent = Base64.getEncoder().encodeToString("Hello, how are you?".getBytes());
        request.putRaw("attachment", attachmentContent);
        request.putRaw("filename", "abc.txt");

        StartCase startCase = new StartCase(testUser, caseInstanceId, definitions, inputs, null);
        testCase.addStep(startCase, casePlan -> {
            casePlan.print();

            // Wait until sending the email has failed (as there is no mail server running, the task must fail...)
            testCase.getEventListener().awaitPlanItemState("Send test email", State.Failed);
        });

        testCase.runTest();
    }


}
