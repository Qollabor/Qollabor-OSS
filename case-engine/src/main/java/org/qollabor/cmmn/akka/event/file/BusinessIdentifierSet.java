package org.qollabor.cmmn.akka.event.file;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.definition.casefile.PropertyDefinition;
import org.qollabor.cmmn.instance.casefile.CaseFileItem;
import org.qollabor.akka.actor.serialization.json.Value;
import org.qollabor.akka.actor.serialization.json.ValueMap;

import java.io.IOException;

/**
 * Basic event allowing listeners that are interested only in case team member events to do initial filtering.
 */
@Manifest
public class BusinessIdentifierSet extends BusinessIdentifierEvent {
    public final Value value;

    public BusinessIdentifierSet(CaseFileItem caseFileItem, PropertyDefinition property, Value businessIdentifierValue) {
        super(caseFileItem, property);
        this.value = businessIdentifierValue;
    }

    public BusinessIdentifierSet(ValueMap json) {
        super(json);
        this.value = json.get(Fields.value.toString());
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.write(generator);
        writeField(generator, Fields.value, value);
    }
}
