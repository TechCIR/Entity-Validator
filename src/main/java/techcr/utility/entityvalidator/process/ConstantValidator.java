package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.EqualValidator;
import techcr.utility.entityvalidator.type.notation.Constant;
import techcr.utility.entityvalidator.validator.ValidationError;

public class ConstantValidator extends BaseFieldValidator implements FieldValidator {

    public ConstantValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException {
        if (null != value) {
            String valueString = value.toString();
            Constant constant = field.getAnnotation(Constant.class);
            String constantValue = constant.value();
            EqualValidator equalValidator;
            try {
                equalValidator = constant.equalValidator().newInstance();

                if (!equalValidator.isEqual(constantValue, value)) {
                    ValidationError validationError = new ValidationError(fieldName);
                    String errorDesc = constant.errorDesc() + ":" + constant.value();
                    validationError.setErrorDescription(errorDesc);
                    validationError.setActualValue(valueString);
                    errors.add(validationError);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
