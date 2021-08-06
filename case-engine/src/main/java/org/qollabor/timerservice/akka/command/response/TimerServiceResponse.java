/* 
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.timerservice.akka.command.response;

import org.qollabor.akka.actor.command.response.ModelResponse;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.timerservice.akka.command.TimerServiceCommand;

/**
 */
@Manifest
public class TimerServiceResponse extends ModelResponse {
    public TimerServiceResponse(TimerServiceCommand command) {
        super(command);
    }

    public TimerServiceResponse(ValueMap json) {
        super(json);
    }
}
