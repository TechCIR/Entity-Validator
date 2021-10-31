package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.notation.DoubleValueIn;
import techcr.utility.entityvalidator.validator.ValidationError;


public class DoubleValueInValidator extends BaseFieldValidator implements FieldValidator {

    public DoubleValueInValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }
    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {
        if (null != value) {
            Double valInDouble = (Double) value;
            DoubleValueIn intValueIn = field.getAnnotation(DoubleValueIn.class);
            boolean valueShouldIn = intValueIn.shouldIn();
            boolean isContain = Arrays.stream(intValueIn.values())
                .anyMatch(value -> Double.valueOf(value).equals(valInDouble));

            if ((valueShouldIn && !isContain) || (!valueShouldIn && isContain)) {
                ValidationError validationError = new ValidationError(fieldName);
                String errorDesc = intValueIn.errorDesc();
                validationError.setErrorDescription(errorDesc);
                validationError.setActualValue(value.toString());
                errors.add(validationError);
            }
        }
    }
}
