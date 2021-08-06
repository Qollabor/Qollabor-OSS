package org.qollabor.timerservice;

import org.qollabor.akka.actor.command.exception.AuthorizationException;
import org.qollabor.akka.actor.event.ModelEvent;
import org.qollabor.akka.actor.handler.CommandHandler;
import org.qollabor.timerservice.akka.command.TimerServiceCommand;

/**
 * Overwriting default command handler to disable unnecessary security checks
 * Security checks run for tenant and so, but TimerService runs as singleton within JVM
 */
public class TimerCommandHandler extends CommandHandler<TimerServiceCommand, ModelEvent, TimerService> {
    protected TimerCommandHandler(TimerService service, TimerServiceCommand command) {
        super(service, command);
    }

    @Override
    protected AuthorizationException runSecurityChecks() {
        // Need to override default CommandHandler security checking - all timers from all tenants are allowed ...
        return null;
    }
}