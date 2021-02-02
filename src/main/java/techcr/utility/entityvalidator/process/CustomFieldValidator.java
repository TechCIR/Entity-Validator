package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.notation.CustomValidate;
import techcr.utility.entityvalidator.validator.ValidationError;

public abstract class CustomFieldValidator<T> extends BaseFieldValidator implements FieldValidator {

    public CustomFieldValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {
        CustomValidate customValidate = field.getAnnotation(CustomValidate.class);
        validate((T) value, customValidate, errors);
    }

    protected abstract void validate(T value, CustomValidate customValidate, List<ValidationError> errors);

}
