package org.qollabor.cmmn.test;

import org.qollabor.cmmn.test.basic.RepeatRule;
import org.qollabor.cmmn.test.casefile.CaseFileTransitionTest;
import org.qollabor.cmmn.test.casefile.RepetitiveFileItems;
import org.qollabor.cmmn.test.task.TestGetListGetDetails;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    RepeatRule.class,
    RepetitiveFileItems.class,
    CaseFileTransitionTest.class,
    TestGetListGetDetails.class
    })
public class RepetitionTests {

}
