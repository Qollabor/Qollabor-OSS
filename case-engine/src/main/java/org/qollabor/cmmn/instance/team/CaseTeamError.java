package org.qollabor.cmmn.instance.team;

import org.qollabor.akka.actor.command.exception.InvalidCommandException;

/**
 * Thrown when an error is found while trying to modify the case team.
 *
 */
public class CaseTeamError extends InvalidCommandException {
    public CaseTeamError(String string) {
        super(string);
    }
}
