package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;

import techcr.utility.entityvalidator.entity.FieldTest;
import techcr.utility.entityvalidator.validator.ValidationError;


public class DoubleValueInValidatorTest {

    @Test
    public void doubleValueInValidatorTest() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field doubleValueIn = fieldTest.getClass().getDeclaredField("doubleValueIn");

        //No validation
        DoubleValueInValidator doubleValueInValidator = new DoubleValueInValidator(doubleValueIn, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        doubleValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setDoubleValueIn(1.1);
        errors = new ArrayList<>();
        doubleValueInValidator = new DoubleValueInValidator(doubleValueIn, fieldTest);
        doubleValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("doubleValueIn"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("1.1"))));

        fieldTest.setDoubleValueIn(3.2);
        errors = new ArrayList<>();
        doubleValueInValidator = new DoubleValueInValidator(doubleValueIn, fieldTest);
        doubleValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}
