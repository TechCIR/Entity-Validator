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


public class StringValueInValidatorTest {

    @Test
    public void stringValueInValidatorTest() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field stringValueIn = fieldTest.getClass().getDeclaredField("stringValueIn");

        //No validation
        StringValueInValidator stringValueInValidator = new StringValueInValidator(stringValueIn, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        stringValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setStringValueIn("B");
        errors = new ArrayList<>();
        stringValueInValidator = new StringValueInValidator(stringValueIn, fieldTest);
        stringValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("stringValueIn"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("B"))));

        fieldTest.setStringValueIn("AB");
        errors = new ArrayList<>();
        stringValueInValidator = new StringValueInValidator(stringValueIn, fieldTest);
        stringValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}
