package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.type.notation.Mandatory;
import techcr.utility.entityvalidator.validator.ValidationError;

public class FieldMandatoryValidator extends BaseFieldValidator implements FieldValidator {

    public FieldMandatoryValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) {
        Mandatory mandatory = field.getAnnotation(Mandatory.class);
        if (null == value) {
            String errorDesc = mandatory.errorDesc();
            ValidationError validationError = new ValidationError(fieldName);
            validationError.setErrorDescription(errorDesc);
            errors.add(validationError);
        }
    }
}
