package org.qollabor.cmmn.akka.command.team;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.akka.command.response.CaseResponse;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.instance.team.Team;

/**
 * Command to remove a member from the case team, based on the user id of the member.
 *
 */
@Manifest
public class RemoveTeamMember extends CaseTeamMemberCommand {
    public RemoveTeamMember(TenantUser tenantUser, String caseInstanceId, MemberKey key) {
        super(tenantUser, caseInstanceId, key);
    }

    public RemoveTeamMember(ValueMap json) {
        super(json);
    }

    @Override
    public void validate(Case caseInstance) {
        super.validate(caseInstance);
        super.validateMembership(caseInstance, key());
        super.validateWhetherOwnerCanBeRemoved(caseInstance, key());
    }

    @Override
    public CaseResponse process(Case caseInstance) {
        Team caseTeam = caseInstance.getCaseTeam();
        caseTeam.removeMember(key());
        return new CaseResponse(this);
    }
}
