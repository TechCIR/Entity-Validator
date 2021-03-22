package techcr.utility.entityvalidator.validator;

import org.junit.Assert;
import org.junit.Test;

import techcr.utility.entityvalidator.type.ValidatorUtil;

public class ValidationErrorTest {

    @Test
    public void testMessageResolver() {
        String errorDescription = "Error Description";
        String name = "name";
        String resolvedName = "NAME";

        ValidationError validationError = new ValidationError(ValidatorUtil.MESSAGE_KEY_PREFIX + name);
        validationError.setErrorDescription(errorDescription);

        Assert.assertEquals(name, validationError.getFieldName());
        Assert.assertEquals(errorDescription, validationError.getErrorDescription());

        ValidatorUtil.configureResolver(String::toUpperCase);
        validationError = new ValidationError(ValidatorUtil.MESSAGE_KEY_PREFIX + name);
        validationError.setErrorDescription(errorDescription);

        Assert.assertEquals(resolvedName, validationError.getFieldName());
        Assert.assertEquals(errorDescription, validationError.getErrorDescription());

        validationError = new ValidationError(ValidatorUtil.MESSAGE_KEY_PREFIX + name);
        validationError.setErrorDescription(errorDescription);

        Assert.assertEquals(resolvedName, validationError.getFieldName());
        Assert.assertEquals(errorDescription, validationError.getErrorDescription());

        validationError = new ValidationError(ValidatorUtil.MESSAGE_KEY_PREFIX + name);
        validationError.setErrorDescription(ValidatorUtil.MESSAGE_KEY_PREFIX + errorDescription);

        Assert.assertEquals(resolvedName, validationError.getFieldName());
        Assert.assertEquals(errorDescription.toUpperCase(), validationError.getErrorDescription());

        validationError = new ValidationError(ValidatorUtil.MESSAGE_KEY_PREFIX + name,
            ValidatorUtil.MESSAGE_KEY_PREFIX + errorDescription, "");

        Assert.assertEquals(resolvedName, validationError.getFieldName());
        Assert.assertEquals(errorDescription.toUpperCase(), validationError.getErrorDescription());

        validationError = new ValidationError(name,
            ValidatorUtil.MESSAGE_KEY_PREFIX + errorDescription, "");

        Assert.assertEquals(name, validationError.getFieldName());
        Assert.assertEquals(errorDescription.toUpperCase(), validationError.getErrorDescription());

        validationError = new ValidationError(name);
        validationError.setErrorDescription(errorDescription);

        Assert.assertEquals(name, validationError.getFieldName());
        Assert.assertEquals(errorDescription, validationError.getErrorDescription());
    }

}
