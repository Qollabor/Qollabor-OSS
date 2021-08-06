/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.cmmn.expression;

import org.qollabor.cmmn.definition.sentry.IfPartDefinition;
import org.qollabor.cmmn.definition.parameter.InputParameterDefinition;
import org.qollabor.cmmn.definition.parameter.ParameterDefinition;
import org.qollabor.cmmn.instance.*;
import org.qollabor.akka.actor.serialization.json.Value;
import org.qollabor.cmmn.definition.ApplicabilityRuleDefinition;
import org.qollabor.cmmn.definition.ConstraintDefinition;
import org.qollabor.cmmn.definition.DiscretionaryItemDefinition;
import org.qollabor.cmmn.definition.TimerEventDefinition;
import org.qollabor.cmmn.instance.sentry.Criterion;

import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 * Simplest implementation of the CMMNExpressionEvaluator. Intended to return default values
 * for expressions such as repetition rule, etc. Simply returns the default value upon evaluation.
 *
 */
public class DefaultValueEvaluator implements CMMNExpressionEvaluator {
    private final boolean defaultValue;

    public DefaultValueEvaluator(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Value<?> evaluateInputParameterTransformation(Case caseInstance, Parameter<InputParameterDefinition> from, ParameterDefinition to, Task<?> task) {
        return from.getValue();
    }

    @Override
    public Value<?> evaluateOutputParameterTransformation(Case caseInstance, Value<?> value, ParameterDefinition rawOutputParameterDefinition, ParameterDefinition targetParameterDefinition, Task<?> task) {
        return value;
    }

    @Override
    public Duration evaluateTimerExpression(TimerEvent timerEvent, TimerEventDefinition definition) {
        try {
            return Duration.parse(definition.getTimerExpression().getBody().trim());
        } catch (DateTimeParseException dtpe) {
            throw new InvalidExpressionException("The timer expression " + definition.getTimerExpression().getBody() + " in " + definition.getName() + " cannot be parsed into a Duration", dtpe);
        }
    }

    @Override
    public boolean evaluateItemControl(PlanItem planItem, ConstraintDefinition ruleDefinition) {
        return defaultValue;
    }

    @Override
    public boolean evaluateIfPart(Criterion criterion, IfPartDefinition ifPartDefinition) {
        return defaultValue;
    }

    @Override
    public boolean evaluateApplicabilityRule(PlanItem containingPlanItem, DiscretionaryItemDefinition discretionaryItemDefinition, ApplicabilityRuleDefinition ruleDefinition) {
        return defaultValue;
    }

}
