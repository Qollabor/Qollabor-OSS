package org.qollabor.cmmn.definition.casefile.definitiontype;

import java.util.Map;

import org.qollabor.cmmn.definition.casefile.CaseFileError;
import org.qollabor.cmmn.definition.casefile.CaseFileItemDefinition;
import org.qollabor.cmmn.definition.casefile.DefinitionType;
import org.qollabor.cmmn.definition.casefile.PropertyDefinition;
import org.qollabor.akka.actor.serialization.json.Value;

public class JSONType extends DefinitionType {

    @Override
    public void validate(CaseFileItemDefinition itemDefinition, Value value, boolean onlyProperties) throws CaseFileError {
        if (value.isMap()) {
            Map<String, PropertyDefinition> properties = itemDefinition.getCaseFileItemDefinition().getProperties();
            if (properties.isEmpty()) {
                // Simply allow to dump the contents and don't do any further validation.
                return;
            }

            // Now iterate the object fields and validate each item.
            value.asMap().getValue().forEach((fieldName, fieldValue) -> {

                // First check to see if it matches one of the properties,
                // and if not, go check for a child item.
                PropertyDefinition propertyDefinition = properties.get(fieldName);
                if (propertyDefinition != null) {
                    validateProperty(propertyDefinition, fieldValue); // Validation may throw TransitionDeniedException
                } else {
                    CaseFileItemDefinition childDefinition = itemDefinition.getChild(fieldName);
                    if (onlyProperties) {
                        if (childDefinition != null) {
                            childDefinition.validatePropertyTypes(fieldValue);
                        }
                    } else {
                        if (childDefinition == null) {
                            throw new CaseFileError("Property '" + fieldName + "' is not found in the definition of "+itemDefinition.getName());
                        }
                        childDefinition.validate(fieldValue);
                    }
                }
            });
        }
    }

    private void validateProperty(PropertyDefinition propertyDefinition, Value propertyValue) {
        if (propertyValue == null || propertyValue == Value.NULL) { // Null-valued properties match any type, let's just continue.
            return;
        }
        PropertyDefinition.PropertyType type = propertyDefinition.getPropertyType();
        try {
            if (!propertyValue.matches(type)) {
                throw new CaseFileError("Property '" + propertyDefinition.getName() + "' has wrong type, expecting " + type);
            }
        } catch (IllegalArgumentException improperType) {
            throw new CaseFileError("Property '" + propertyDefinition.getName() + "' has wrong type, expecting " + type + ", found exception " + improperType.getMessage());
        }
    }
}
