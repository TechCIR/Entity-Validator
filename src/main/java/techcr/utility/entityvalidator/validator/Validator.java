package techcr.utility.entityvalidator.validator;

import java.util.ArrayList;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.process.EntityFieldValidator;


public class Validator<T> {

    private List<ValidationError> validationErrors;

    public void validate(T bean) throws IllegalAccessException, UnsupportedFieldException {
        validate(bean, null);
    }

    public void validate(T bean, List<ValidationError> errors) throws IllegalAccessException, UnsupportedFieldException {
        this.validationErrors =  null == errors ? new ArrayList<>() : errors;

        EntityFieldValidator entityFieldValidator = new EntityFieldValidator(bean);
        entityFieldValidator.validate(validationErrors);

    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
