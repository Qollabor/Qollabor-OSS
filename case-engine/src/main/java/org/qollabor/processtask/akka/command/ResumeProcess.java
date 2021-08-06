/*
 * Copyright 2014 - 2019 Qollabor B.V.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.qollabor.processtask.akka.command;

import org.qollabor.akka.actor.identity.TenantUser;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.processtask.akka.command.response.ProcessResponse;
import org.qollabor.processtask.instance.ProcessTaskActor;

@Manifest
public class ResumeProcess extends ProcessCommand {
    public ResumeProcess(TenantUser tenantUser, String id) {
        super(tenantUser, id);
    }

    public ResumeProcess(ValueMap json) {
        super(json);
    }

    @Override
    public ProcessResponse process(ProcessTaskActor process) {
        return process.resume(this);
    }
}
