package org.qollabor.cmmn.test.basic;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.cmmn.akka.command.StartCase;
import org.qollabor.cmmn.definition.CaseDefinition;
import org.qollabor.cmmn.instance.State;
import org.qollabor.cmmn.instance.Transition;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.test.TestScript;
import org.qollabor.humantask.akka.command.CompleteHumanTask;
import org.qollabor.humantask.akka.event.HumanTaskAssigned;
import org.qollabor.humantask.akka.event.HumanTaskDueDateFilled;
import org.junit.Test;

public class HelloWorldTest {
    private final CaseDefinition definitions = TestScript.getCaseDefinition("testdefinition/helloworld.xml");
    private final TenantUser testUser = TestScript.getTestUser("Anonymous");

    @Test
    public void testHelloWorld() {
        String caseInstanceId = "HelloWorldTest";
        TestScript testCase = new TestScript("hello-world");
        ValueMap greeting = new ValueMap("Greeting", new ValueMap("Message", "hello", "To", testUser.id(), "From", testUser.id()));

        StartCase startCase = new StartCase(testUser, caseInstanceId, definitions, greeting, null);
        testCase.addStep(startCase, casePlan -> {
            casePlan.print();
            String taskId = casePlan.assertHumanTask("Receive Greeting and Send response").getId();
            casePlan.getEvents().filter(HumanTaskDueDateFilled.class).assertSize(1);
            casePlan.getEvents().filter(HumanTaskAssigned.class).assertSize(1);

            CompleteHumanTask completeTask1 = new CompleteHumanTask(testUser, caseInstanceId, taskId, new ValueMap());
            testCase.insertStep(completeTask1, casePlan2 -> {
                casePlan2.print();
                casePlan2.assertLastTransition(Transition.Create, State.Active, State.Null);
                casePlan2.assertPlanItem("Receive Greeting and Send response").assertState(State.Completed);
                casePlan2.assertPlanItem("Read response").assertState(State.Active);

            });
        });

        testCase.runTest();
    }

    @Test
    public void testHelloWorldWithoutAssignee() {
        String caseInstanceId = "HelloWorldTest";
        TestScript testCase = new TestScript("hello-world");
        ValueMap greeting = new ValueMap("Greeting", new ValueMap("Message", "hello", "To", "", "From", testUser.id()));

        StartCase startCase = new StartCase(testUser, caseInstanceId, definitions, greeting, null);
        testCase.addStep(startCase, action -> {
            TestScript.debugMessage("Events: " + action.getTestCommand());
            action.getEvents().filter(HumanTaskDueDateFilled.class).assertSize(1);
            action.getEvents().filter(HumanTaskAssigned.class).assertSize(0);
        });

        testCase.runTest();
    }
}
