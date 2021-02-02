package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import techcr.utility.entityvalidator.entity.FieldTest;
import techcr.utility.entityvalidator.entity.User;
import techcr.utility.entityvalidator.validator.ValidationError;

public class EntityFieldValidatorTest {

    @Test
    public void validateBean() throws Exception {

        FieldTest fieldTest = new FieldTest();
        EntityFieldValidator<FieldTest> entityFieldValidator = new EntityFieldValidator<>(fieldTest);

        List<ValidationError> validationErrors = new ArrayList<>();
        entityFieldValidator.validate(validationErrors);

        System.out.println(validationErrors.size());

    }

    @Test
    public void validateBeanAsField() throws Exception {

        FieldTest fieldTest = new FieldTest();
        User user = new User();
        user.setAge(20);
        fieldTest.setUser(user);

        Field field = fieldTest.getClass().getDeclaredField("user");

        EntityFieldValidator<FieldTest> entityFieldValidator = new EntityFieldValidator<>(field, fieldTest);

        List<ValidationError> validationErrors = new ArrayList<>();
        entityFieldValidator.validate(validationErrors);

        System.out.println(validationErrors.size());

    }
}
