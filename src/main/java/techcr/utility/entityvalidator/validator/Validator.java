package techcr.utility.entityvalidator.validator;

import java.util.ArrayList;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.process.EntityFieldValidator;

public interface Validator<T> {

    default void validate(T bean) throws IllegalAccessException, UnsupportedFieldException {
        validate(bean, null);
    }

    void validate(T bean, List<ValidationError> errors) throws IllegalAccessException, UnsupportedFieldException;

    List<ValidationError> getValidationErrors();
}
