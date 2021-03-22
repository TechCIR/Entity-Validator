package techcr.utility.entityvalidator.process;

import java.util.List;

import techcr.utility.entityvalidator.validator.ValidationError;


public interface CustomEntityValidator<T> {
    void validate(T t, List<ValidationError> validationErrorList);
}
