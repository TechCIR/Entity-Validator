package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.NumberValueConvertor;
import techcr.utility.entityvalidator.type.notation.Range;
import techcr.utility.entityvalidator.validator.ValidationError;


public class FieldRangeValidator extends BaseFieldValidator implements FieldValidator {

    public FieldRangeValidator (Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException {
        if (null != value) {
            if (value instanceof Number) {
                Range range = field.getAnnotation(Range.class);
                NumberValueConvertor numberValueConvertor = new NumberValueConvertor();
                String valueString = value.toString();
                Double numValue = numberValueConvertor.convert(valueString).doubleValue();
                Double minValue = numberValueConvertor.convert(range.minValue()).doubleValue();
                Double maxValue = numberValueConvertor.convert(range.maxValue()).doubleValue();
                boolean isValid = false;
                if (numValue >= minValue && numValue <= maxValue) {
                    isValid = true;
                }
                if (!isValid) {
                    ValidationError validationError = new ValidationError(fieldName);
                    String errorDesc = range.errorDesc() + " " + range.minValue() + " & " + range.maxValue();
                    validationError.setErrorDescription(errorDesc);
                    validationError.setActualValue(valueString);
                    errors.add(validationError);
                }
            }
        }
    }
}
