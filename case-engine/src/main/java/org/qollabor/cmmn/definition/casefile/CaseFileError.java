package org.qollabor.cmmn.definition.casefile;

import org.qollabor.akka.actor.command.exception.InvalidCommandException;

/**
 * Thrown when an error is found while trying to modify the case file.
 *
 */
public class CaseFileError extends InvalidCommandException {
    public CaseFileError(String string) {
        super(string);
    }
}
