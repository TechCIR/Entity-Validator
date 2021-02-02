package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import techcr.utility.entityvalidator.entity.FieldTest;
import techcr.utility.entityvalidator.entity.TestEnum;
import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.validator.ValidationError;

public class EnumTypeValidatorTest {

    @Test
    public void validateToEnumAny() throws NoSuchFieldException, IllegalAccessException, UnsupportedFieldException {

        FieldTest fieldTest = new FieldTest();
        fieldTest.setTestEnumAny(TestEnum.UNKNOWN);
        Field testEnumAny = fieldTest.getClass().getDeclaredField("testEnumAny");

        EnumTypeValidator enumTypeValidator = new EnumTypeValidator(testEnumAny, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(0, errors.size());
    }

    @Test
    public void validateInvalidIndex() throws NoSuchFieldException, IllegalAccessException, UnsupportedFieldException {

        FieldTest fieldTest = new FieldTest();
        fieldTest.setInvalidIndex(TestEnum.UNKNOWN);
        Field testInvalidIndex = fieldTest.getClass().getDeclaredField("invalidIndex");

        EnumTypeValidator enumTypeValidator = new EnumTypeValidator(testInvalidIndex, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(1, errors.size());

    }

    @Test
    public void validateEqualTo() throws NoSuchFieldException, IllegalAccessException, UnsupportedFieldException {

        FieldTest fieldTest = new FieldTest();
        fieldTest.setTestEnumEqualTo(TestEnum.SUCCESS);
        Field testEnumEqualTo = fieldTest.getClass().getDeclaredField("testEnumEqualTo");

        EnumTypeValidator enumTypeValidator = new EnumTypeValidator(testEnumEqualTo, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
        System.out.println(errors.get(0).toString());

        fieldTest.setTestEnumEqualTo(TestEnum.FAILED);
        testEnumEqualTo = fieldTest.getClass().getDeclaredField("testEnumEqualTo");

        enumTypeValidator = new EnumTypeValidator(testEnumEqualTo, fieldTest);
        errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(0, errors.size());

    }

    @Test
    public void validateNotEqualTo() throws NoSuchFieldException, IllegalAccessException, UnsupportedFieldException {
        FieldTest fieldTest = new FieldTest();
        fieldTest.setTestEnumAnyNotEqualTo(TestEnum.UNKNOWN);
        Field testEnumNotEqualTo = fieldTest.getClass().getDeclaredField("testEnumAnyNotEqualTo");

        EnumTypeValidator enumTypeValidator = new EnumTypeValidator(testEnumNotEqualTo, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(1, errors.size());
        System.out.println(errors.get(0).toString());

        fieldTest.setTestEnumAnyNotEqualTo(TestEnum.SUCCESS);
        testEnumNotEqualTo = fieldTest.getClass().getDeclaredField("testEnumAnyNotEqualTo");
        enumTypeValidator = new EnumTypeValidator(testEnumNotEqualTo, fieldTest);
        errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(0, errors.size());

        fieldTest.setTestEnumAnyNotEqualTo(TestEnum.FAILED);
        testEnumNotEqualTo = fieldTest.getClass().getDeclaredField("testEnumAnyNotEqualTo");
        enumTypeValidator = new EnumTypeValidator(testEnumNotEqualTo, fieldTest);
        errors = new ArrayList<>();
        enumTypeValidator.validate(errors);
        Assert.assertEquals(0, errors.size());
    }


}
