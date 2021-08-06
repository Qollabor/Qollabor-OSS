package org.qollabor.akka.actor.handler;

import org.qollabor.akka.actor.MessageHandler;
import org.qollabor.akka.actor.ModelActor;
import org.qollabor.akka.actor.command.ModelCommand;
import org.qollabor.akka.actor.event.ModelEvent;
import org.qollabor.akka.actor.identity.TenantUser;

public class AkkaSystemMessageHandler<C extends ModelCommand, E extends ModelEvent, A extends ModelActor<C, E>> extends MessageHandler<Object, C, E, A> {

    public AkkaSystemMessageHandler(A actor, Object msg) {
        super(actor, msg, TenantUser.NONE());
    }

    protected void process() {
    }

    protected void complete() {
    }
}
