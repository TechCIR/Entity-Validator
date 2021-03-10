package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import techcr.utility.entityvalidator.entity.FieldTest;
import techcr.utility.entityvalidator.validator.ValidationError;

public class NumberFieldValidatorTest {

    @Test
    public void testEqualLongValue() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setGreaterThanOrEqualNumberLong(10L);
        Field field = fieldTest.getClass().getDeclaredField("greaterThanOrEqualNumberLong");
        List<ValidationError> errors = new ArrayList<>();
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator(field, fieldTest);
        numberFieldValidator.validate(errors);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testGreaterThanLongValue() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setGreaterThanOrEqualNumberLong(20L);
        Field field = fieldTest.getClass().getDeclaredField("greaterThanOrEqualNumberLong");
        List<ValidationError> errors = new ArrayList<>();
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator(field, fieldTest);
        numberFieldValidator.validate(errors);
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testLessThanLongValueWithError() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setGreaterThanOrEqualNumberLong(5L);
        Field field = fieldTest.getClass().getDeclaredField("greaterThanOrEqualNumberLong");
        List<ValidationError> errors = new ArrayList<>();
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator(field, fieldTest);
        numberFieldValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
    }

    @Test
    public void testEqualDoubleValueWithError() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setLessThanNumberDouble(20.5);
        Field field = fieldTest.getClass().getDeclaredField("lessThanNumberDouble");
        List<ValidationError> errors = new ArrayList<>();
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator(field, fieldTest);
        numberFieldValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
    }

    @Test
    public void testGreaterThanDoubleValueWithError() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setLessThanNumberDouble(21d);
        Field field = fieldTest.getClass().getDeclaredField("lessThanNumberDouble");
        List<ValidationError> errors = new ArrayList<>();
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator(field, fieldTest);
        numberFieldValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
    }

    @Test
    public void testLessThanDoubleValueWithoutError() throws Exception {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setLessThanNumberDouble(20d);
        Field field = fieldTest.getClass().getDeclaredField("lessThanNumberDouble");
        List<ValidationError> errors = new ArrayList<>();
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator(field, fieldTest);
        numberFieldValidator.validate(errors);
        Assert.assertEquals(0, errors.size());
    }

}
