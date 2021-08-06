package org.qollabor.cmmn.definition.task.validation;

import org.qollabor.cmmn.instance.task.humantask.HumanTask;
import org.qollabor.cmmn.instance.task.validation.TaskOutputValidator;
import org.qollabor.processtask.definition.ProcessDefinition;
import org.qollabor.processtask.implementation.http.HTTPCallDefinition;

public class TaskOutputValidatorDefinition {
    private final ProcessDefinition processDef;
    private final HTTPCallDefinition httpDefinition;

    public TaskOutputValidatorDefinition(ProcessDefinition definition) {
        this.processDef = definition;
        if (! (this.processDef.getImplementation() instanceof HTTPCallDefinition)) {
            definition.addDefinitionError("Task validator "+definition+"");
        }
        this.httpDefinition = (HTTPCallDefinition) this.processDef.getImplementation();
    }

    public TaskOutputValidator createInstance(HumanTask task) {
        return this.httpDefinition.createValidator(task);
    }
}
