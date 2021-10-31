package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.notation.StringValueIn;
import techcr.utility.entityvalidator.validator.ValidationError;

public class StringValueInValidator extends BaseFieldValidator implements FieldValidator {

    public StringValueInValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }
    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {
        if (null != value) {
            String valString = value.toString();
            StringValueIn stringValueIn = field.getAnnotation(StringValueIn.class);
            boolean valueShouldIn = stringValueIn.shouldIn();
            List<String> values = Arrays.asList(stringValueIn.values());
            boolean isContain = values.contains(valString);

            if ((valueShouldIn && !isContain) || (!valueShouldIn && isContain)) {
                ValidationError validationError = new ValidationError(fieldName);
                String errorDesc = stringValueIn.errorDesc();
                validationError.setErrorDescription(errorDesc);
                validationError.setActualValue(valString);
                errors.add(validationError);
            }
        }
    }
}
