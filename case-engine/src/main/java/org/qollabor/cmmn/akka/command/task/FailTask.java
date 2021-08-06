package org.qollabor.cmmn.akka.command.task;

import org.qollabor.akka.actor.ModelActor;
import org.qollabor.cmmn.akka.command.response.CaseResponse;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.akka.actor.serialization.json.ValueMap;

@Manifest
public class FailTask extends CompleteTask {
    public FailTask(ModelActor child, ValueMap taskOutput) {
        super(child, taskOutput);
    }

    public FailTask(ValueMap json) {
        super(json);
    }

    @Override
    public CaseResponse process(Case caseInstance) {
        task.goFault(taskOutput);
        return new CaseResponse(this);
    }
}
