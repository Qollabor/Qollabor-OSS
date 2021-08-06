/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.timerservice.akka.command;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.instance.TimerEvent;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.timerservice.TimerService;
import org.qollabor.timerservice.akka.command.response.TimerServiceResponse;

/**
 *
 */
@Manifest
public class CancelTimer extends TimerServiceCommand {
    /**
     * Ask timer service to cancel a timer when it is no longer needed
     *
     */
    public CancelTimer(TenantUser tenantUser, TimerEvent timer) {
        super(tenantUser, timer.getId());
    }

    public CancelTimer(ValueMap json) {
        super(json);
    }

    /**
     */
    public TimerServiceResponse process(TimerService service) {
        return service.handle(this);
    }
}
