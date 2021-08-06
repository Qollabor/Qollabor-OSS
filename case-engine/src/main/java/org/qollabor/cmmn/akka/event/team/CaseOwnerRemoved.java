package org.qollabor.cmmn.akka.event.team;

import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.akka.command.team.MemberKey;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.akka.actor.serialization.json.ValueMap;

/**
 * Event caused when a team member is no longer owner
 */
@Manifest
public class CaseOwnerRemoved extends CaseTeamMemberEvent {

    public CaseOwnerRemoved(Case caseInstance, MemberKey member) {
        super(caseInstance, member);
    }

    public CaseOwnerRemoved(ValueMap json) {
        super(json);
    }

    @Override
    public String getDescription() {
        return getClass().getSimpleName() + "[" + getMemberDescription()+" is no longer owner]";
    }

    @Override
    public void updateState(Case actor) {
        actor.getCaseTeam().updateState(this);
    }
}
