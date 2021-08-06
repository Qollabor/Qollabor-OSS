package org.qollabor.cmmn.akka.command.response;

import org.qollabor.cmmn.akka.command.AddDiscretionaryItem;
import org.qollabor.cmmn.akka.command.GetDiscretionaryItems;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;

/**
 * Response to a {@link GetDiscretionaryItems} command
 */
@Manifest
public class AddDiscretionaryItemResponse extends CaseResponseWithValueMap {
    public AddDiscretionaryItemResponse(AddDiscretionaryItem command, ValueMap value) {
        super(command, value);
    }

    public AddDiscretionaryItemResponse(ValueMap json) {
        super(json);
    }
}
