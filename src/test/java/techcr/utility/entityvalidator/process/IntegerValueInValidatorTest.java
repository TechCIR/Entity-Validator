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


public class IntegerValueInValidatorTest {

    @Test
    public void integerValueInValidatorTest() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field intValueIn = fieldTest.getClass().getDeclaredField("intValueIn");

        //No validation
        IntegerValueInValidator intValueInValidator = new IntegerValueInValidator(intValueIn, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        intValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setIntValueIn(3);
        errors = new ArrayList<>();
        intValueInValidator = new IntegerValueInValidator(intValueIn, fieldTest);
        intValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("intValueIn"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("3"))));

        fieldTest.setIntValueIn(2);
        errors = new ArrayList<>();
        intValueInValidator = new IntegerValueInValidator(intValueIn, fieldTest);
        intValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}
