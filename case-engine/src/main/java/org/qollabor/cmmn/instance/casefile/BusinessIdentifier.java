package org.qollabor.cmmn.instance.casefile;

import org.qollabor.akka.actor.serialization.json.Value;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.qollabor.cmmn.akka.event.file.BusinessIdentifierCleared;
import org.qollabor.cmmn.akka.event.file.BusinessIdentifierEvent;
import org.qollabor.cmmn.akka.event.file.BusinessIdentifierSet;
import org.qollabor.cmmn.definition.casefile.PropertyDefinition;

class BusinessIdentifier {
    private final CaseFileItem item;
    private final PropertyDefinition definition;
    private final String name;
    private Value currentValue = null;

    BusinessIdentifier(CaseFileItem item, PropertyDefinition property) {
        this.item = item;
        this.definition = property;
        this.name = property.getName();
    }

    void clear() {
        item.getCaseInstance().addEvent(new BusinessIdentifierCleared(item, definition));
    }

    void update(ValueMap map) {
        if (map.has(name)) {
            Value potentialNewValue = map.get(name);
            if (! potentialNewValue.equals(currentValue)) {
                item.getCaseInstance().addEvent(new BusinessIdentifierSet(item, definition, potentialNewValue));
            }
        }
    }

    void updateState(BusinessIdentifierEvent event) {
        this.currentValue = event.getValue();
    }
}
