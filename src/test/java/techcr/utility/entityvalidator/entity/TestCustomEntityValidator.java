package techcr.utility.entityvalidator.entity;

import java.util.List;

import techcr.utility.entityvalidator.process.CustomEntityValidator;
import techcr.utility.entityvalidator.validator.ValidationError;

public class TestCustomEntityValidator implements CustomEntityValidator<FieldTest> {

    @Override
    public void validate(FieldTest fieldTest, List<ValidationError> validationErrorList) {
        if (fieldTest.getName() == null || fieldTest.getName().isEmpty()) {
            validationErrorList.add(new ValidationError("name", "Name Empty Catch By Custom Validator", ""));
        }
    }
}
