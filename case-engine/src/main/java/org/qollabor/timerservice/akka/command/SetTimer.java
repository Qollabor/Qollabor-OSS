/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.timerservice.akka.command;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.instance.TimerEvent;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.timerservice.TimerService;
import org.qollabor.timerservice.akka.command.response.TimerServiceResponse;

import java.io.IOException;
import java.time.Instant;

/**
 *
 */
@Manifest
public class SetTimer extends TimerServiceCommand {
    public final String caseInstanceId;
    public final Instant moment;

    /**
     * Ask timer service to ping the case task when the moment has come
     *
     */
    public SetTimer(TenantUser tenantUser, TimerEvent timer, Instant moment) {
        super(tenantUser, timer.getId());
        this.caseInstanceId = timer.getCaseInstance().getId();
        this.moment = moment;
    }

    public SetTimer(ValueMap json) {
        super(json);
        this.caseInstanceId = readField(json, Fields.caseInstanceId);
        this.moment = readInstant(json, Fields.moment);
    }

    /**
     */
    public TimerServiceResponse process(TimerService service) {
        return service.handle(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.caseInstanceId, caseInstanceId);
        writeField(generator, Fields.moment, moment);
    }
}
