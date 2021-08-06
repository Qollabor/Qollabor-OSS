package org.qollabor.cmmn.akka.command.response;

import org.qollabor.cmmn.akka.command.GetDiscretionaryItems;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.akka.actor.serialization.json.ValueMap;

/**
 * Response to a {@link GetDiscretionaryItems} command
 */
@Manifest
public class GetDiscretionaryItemsResponse extends CaseResponseWithValueMap {
    public GetDiscretionaryItemsResponse(GetDiscretionaryItems command, ValueMap value) {
        super(command, value);
    }

    public GetDiscretionaryItemsResponse(ValueMap json) {
        super(json);
    }

    /**
     * Returns a JSON representation of the discretionary items that are currently applicable in the case
     * @return
     */
    public ValueMap getItems() {
        return getResponse();
    }
}
