package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;

import techcr.utility.entityvalidator.entity.FieldTest;
import techcr.utility.entityvalidator.entity.TestEnum;
import techcr.utility.entityvalidator.validator.ValidationError;

public class ConditionValidatorTest {

    @Test
    public void classLevelConditionValidationTest() throws Exception {

        FieldTest fieldTest = new FieldTest();

        ConditionValidator conditionValidator = new ConditionValidator(fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setConditionValues("Chamly");
        errors = new ArrayList<>();
        conditionValidator = new ConditionValidator(fieldTest);
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));
    }

    @Test
    public void fieldLevelValueEqualConditionValidation() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field rule = fieldTest.getClass().getDeclaredField("conditionValueCheck");

        fieldTest.setConditionValueCheck(1);

        ConditionValidator conditionValidator = new ConditionValidator(rule, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));
        fieldTest.setConditionValueCheck(1);

        fieldTest.setConditionValues("One");
        conditionValidator = new ConditionValidator(rule, fieldTest);
        errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setConditionValueCheck(2);
        conditionValidator = new ConditionValidator(rule, fieldTest);
        errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setConditionDepenField("One");
        conditionValidator = new ConditionValidator(rule, fieldTest);
        errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setConditionValues(null);
        conditionValidator = new ConditionValidator(rule, fieldTest);
        errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));
    }

    @Test
    public void fieldLevelConditionValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field rule = fieldTest.getClass().getDeclaredField("conditionValues2");

        ConditionValidator conditionValidator = new ConditionValidator(rule, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setConditionValues2("Yes");
        errors = new ArrayList<>();
        conditionValidator = new ConditionValidator(rule, fieldTest);
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setConditionDepenField("Yes");
        errors = new ArrayList<>();
        conditionValidator = new ConditionValidator(rule, fieldTest);
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setConditionValues("Failed");
        errors = new ArrayList<>();
        conditionValidator = new ConditionValidator(rule, fieldTest);
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

    }

    @Test
    public void enumLevelConditionValidation() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field enumRule = fieldTest.getClass().getDeclaredField("conditionEnumValue");

        ConditionValidator conditionValidator = new ConditionValidator(enumRule, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setConditionEnumValue("Yes");
        fieldTest.setTestEnum(TestEnum.SUCCESS);
        errors = new ArrayList<>();
        conditionValidator = new ConditionValidator(enumRule, fieldTest);
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setConditionEnumValue("Yes");
        fieldTest.setTestEnum(TestEnum.FAILED);
        errors = new ArrayList<>();
        conditionValidator = new ConditionValidator(enumRule, fieldTest);
        conditionValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
    }
}
