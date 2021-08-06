package org.qollabor.cmmn.instance.sentry;

import org.qollabor.cmmn.definition.sentry.ExitCriterionDefinition;
import org.qollabor.cmmn.instance.PlanItemExit;

public class ExitCriterion extends Criterion<ExitCriterionDefinition> {
    public ExitCriterion(PlanItemExit target, ExitCriterionDefinition definition) {
        super(target, definition);
    }

    @Override
    protected void satisfy() {
        target.satisfy(this);
    }

    @Override
    public boolean isEntryCriterion() {
        return false;
    }
}