package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.notation.EnumType;
import techcr.utility.entityvalidator.validator.ValidationError;

public class EnumTypeValidator extends BaseFieldValidator implements FieldValidator {

    public EnumTypeValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {
        if (null != value) {
            EnumType enumType = field.getAnnotation(EnumType.class);
            EnumSet enumSet = EnumSet.allOf(enumType.clazz());
            if (enumType.validAny()) {
                if (!enumSet.contains(value)) {
                    addValidationError(enumType.errorDesc(), errors);
                }
            } else {
                Object[] valueArray = enumSet.toArray();
                int constantIndex = enumType.equalTo() > -1 ? enumType.equalTo() : enumType.notEqualTo();
                if (constantIndex < 0 || constantIndex >= valueArray.length) {
                    addValidationError(enumType.invalidIndexError() + constantIndex, errors);
                } else {
                    Object requiredValue = valueArray[constantIndex];
                    if ( (enumType.equalTo() > -1 && !value.equals(requiredValue)) ||
                        ( enumType.notEqualTo() > -1 && value.equals(requiredValue))) {
                        addValidationError(enumType.errorDesc(), errors);
                    }
                }
            }
        }
    }

    private void addValidationError(String errorDesc, List<ValidationError> errors) {
        ValidationError validationError = new ValidationError(fieldName);
        validationError.setErrorDescription(errorDesc);
        validationError.setActualValue(value.toString());
        errors.add(validationError);
    }
}
