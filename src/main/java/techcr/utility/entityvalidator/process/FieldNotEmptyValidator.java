package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.type.notation.NotEmpty;
import techcr.utility.entityvalidator.validator.ValidationError;

public class FieldNotEmptyValidator extends BaseFieldValidator implements FieldValidator {

    public FieldNotEmptyValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) {
        NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
        if (null == value || (value instanceof String && ((String) value).length() == 0)) {
            String errorDesc = notEmpty.errorDesc();
            ValidationError validationError = new ValidationError(fieldName);
            validationError.setErrorDescription(errorDesc);
            errors.add(validationError);
        }
    }
}
