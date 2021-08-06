/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.test;

import org.qollabor.cmmn.instance.ValueTests;
import org.qollabor.cmmn.instance.process.StringTemplateTest;
import org.qollabor.cmmn.test.basic.BasicTests;
import org.qollabor.cmmn.test.casefile.CaseFileTests;
import org.qollabor.cmmn.test.expression.ExpressionTests;
import org.qollabor.cmmn.test.task.TaskTests;
import org.qollabor.cmmn.test.team.TeamTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        BasicTests.class,
        CaseFileTests.class,
        ExpressionTests.class,
        TaskTests.class,
        TeamTests.class,
        ValueTests.class,
        StringTemplateTest.class
})
public class AllTests {

}
