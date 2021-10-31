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


public class LongValueInValidatorTest {

    @Test
    public void longValueInValidatorTest() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field longValueIn = fieldTest.getClass().getDeclaredField("longValueIn");

        //No validation
        LongValueInValidator longValueInValidator = new LongValueInValidator(longValueIn, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        longValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLongValueIn(5L);
        errors = new ArrayList<>();
        longValueInValidator = new LongValueInValidator(longValueIn, fieldTest);
        longValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("longValueIn"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("5"))));

        fieldTest.setLongValueIn(3L);
        errors = new ArrayList<>();
        longValueInValidator = new LongValueInValidator(longValueIn, fieldTest);
        longValueInValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}
