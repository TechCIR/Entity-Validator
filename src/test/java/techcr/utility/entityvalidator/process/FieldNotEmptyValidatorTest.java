package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import techcr.utility.entityvalidator.entity.FieldTest;
import techcr.utility.entityvalidator.validator.ValidationError;

public class FieldNotEmptyValidatorTest {

    @Test
    public void testNotEmptyWithValue() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setNotEmptyString("Not empty");
        Field field = fieldTest.getClass().getDeclaredField("notEmptyString");
        List<ValidationError> errors = new ArrayList<>();
        FieldNotEmptyValidator notEmptyValidator = new FieldNotEmptyValidator(field, fieldTest);
        notEmptyValidator.validate(errors);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testNotEmptyWithoutValue() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setNotEmptyString("");
        Field field = fieldTest.getClass().getDeclaredField("notEmptyString");
        List<ValidationError> errors = new ArrayList<>();
        FieldNotEmptyValidator notEmptyValidator = new FieldNotEmptyValidator(field, fieldTest);
        notEmptyValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
    }

    @Test
    public void testNotEmptyWithNullValue() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setNotEmptyString(null);
        Field field = fieldTest.getClass().getDeclaredField("notEmptyString");
        List<ValidationError> errors = new ArrayList<>();
        FieldNotEmptyValidator notEmptyValidator = new FieldNotEmptyValidator(field, fieldTest);
        notEmptyValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
    }

}
