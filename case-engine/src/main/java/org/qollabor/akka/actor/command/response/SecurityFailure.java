/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.akka.actor.command.response;

import org.qollabor.akka.actor.command.ModelCommand;
import org.qollabor.akka.actor.command.exception.AuthorizationException;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;

/**
 * Can be used to return an exception to the sender of the command.
 */
@Manifest
public class SecurityFailure extends CommandFailure {

    /**
     * Create a failure response for the command.
     * The message id of the command will be pasted into the message id of the response.
     * @param command
     * @param failure The reason why the command failed
     */
    public SecurityFailure(ModelCommand command, AuthorizationException failure) {
        super(command, failure);
    }

    public SecurityFailure(ValueMap json) {
        super(json);
    }
}
