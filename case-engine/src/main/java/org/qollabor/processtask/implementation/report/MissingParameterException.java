package org.qollabor.processtask.implementation.report;

import org.qollabor.akka.actor.command.exception.InvalidCommandException;

public class MissingParameterException extends InvalidCommandException {
    public MissingParameterException(String msg) {
        super(msg);
    }
}
