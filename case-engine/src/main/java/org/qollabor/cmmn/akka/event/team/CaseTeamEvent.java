package org.qollabor.cmmn.akka.event.team;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.cmmn.akka.event.CaseEvent;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.akka.actor.serialization.json.ValueMap;

import java.io.IOException;

/**
 * Basic event allowing listeners that are interested only in team events to do initial filtering.
 */
public abstract class CaseTeamEvent extends CaseEvent {
    protected CaseTeamEvent(Case caseInstance) {
        super(caseInstance);
    }

    protected CaseTeamEvent(ValueMap json) {
        super(json);
    }

    protected void writeCaseTeamEvent(JsonGenerator generator) throws IOException {
        super.writeCaseInstanceEvent(generator);
    }
}
