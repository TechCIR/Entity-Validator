package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.NumberCriteriaType;
import techcr.utility.entityvalidator.type.NumberValueConvertor;
import techcr.utility.entityvalidator.type.notation.NumberField;
import techcr.utility.entityvalidator.validator.ValidationError;

public class NumberFieldValidator extends BaseFieldValidator implements FieldValidator {

    public NumberFieldValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException {
        if (null != value) {
            if (value instanceof Number) {
                NumberField numberField = field.getAnnotation(NumberField.class);
                NumberValueConvertor numberValueConvertor = new NumberValueConvertor();
                String valueString = value.toString();
                Double numValue = numberValueConvertor.convert(valueString).doubleValue();
                Double conditionValue = numberValueConvertor.convert(numberField.number()).doubleValue();
                NumberCriteriaType criteriaType = numberField.criteriaType();
                boolean isValid = criteriaType.isValid(conditionValue, numValue);
                if (!isValid) {
                    ValidationError validationError = new ValidationError(fieldName);
                    String errorDesc = numberField.errorDesc() + " " + numberField.criteriaType().name() + ":" +
                        numberField.number();
                    validationError.setErrorDescription(errorDesc);
                    validationError.setActualValue(valueString);
                    errors.add(validationError);
                }
            }
        }
    }
}
