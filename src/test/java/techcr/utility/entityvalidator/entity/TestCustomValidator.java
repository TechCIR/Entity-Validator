package techcr.utility.entityvalidator.entity;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.process.CustomFieldValidator;
import techcr.utility.entityvalidator.type.notation.CustomValidate;
import techcr.utility.entityvalidator.validator.ValidationError;

public class TestCustomValidator extends CustomFieldValidator<Integer> {

    public TestCustomValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    protected void validate(Integer value, CustomValidate customValidate, List<ValidationError> errors) {
        if (null == value || value == 0) {
            ValidationError validationError = new ValidationError(fieldName);
            validationError.setErrorDescription("Should be grater than 0");
            errors.add(validationError);
        }
    }
}
