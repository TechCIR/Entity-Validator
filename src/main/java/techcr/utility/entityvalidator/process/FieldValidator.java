package techcr.utility.entityvalidator.process;

import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.validator.ValidationError;

public interface FieldValidator {
    void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException;
}
