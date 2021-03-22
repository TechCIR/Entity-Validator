package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.ValidatorUtil;
import techcr.utility.entityvalidator.type.notation.ConditionValidation;
import techcr.utility.entityvalidator.type.notation.CustomEntityValidate;
import techcr.utility.entityvalidator.type.notation.ExcludeParent;
import techcr.utility.entityvalidator.type.notation.ValidatorFieldDescription;
import techcr.utility.entityvalidator.validator.ValidationError;


public class EntityFieldValidator<T> implements FieldValidator {

    private T bean;
    private String fieldName;

    public EntityFieldValidator(T bean) {
        this.bean = bean;
    }

    public EntityFieldValidator(Field field, Object parentEntity) throws IllegalAccessException {
        field.setAccessible(true);
        this.bean = (T) field.get(parentEntity);
        /*if (field.isAnnotationPresent(ValidatorFieldDescription.class)) {
            ValidatorFieldDescription description = field.getAnnotation(ValidatorFieldDescription.class);
            this.fieldName = description.fieldDesc();
        }*/
        this.fieldName = ValidatorUtil.getFieldName(field);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {

        if (null != bean) {
            List<Field> fields;
            if (bean.getClass().isAnnotationPresent(ExcludeParent.class)) {
                fields = Arrays.asList(bean.getClass().getDeclaredFields());
            } else {
                fields = new ArrayList<>();
                getInheritedFields(bean.getClass(), fields);
            }

            if (bean.getClass().isAnnotationPresent(ConditionValidation.class)) {
                ConditionValidator conditionValidator = new ConditionValidator(bean);
                conditionValidator.validate(errors);
            }

            if (bean.getClass().isAnnotationPresent(CustomEntityValidate.class)) {
                CustomEntityValidate customEntityValidate = bean.getClass().getAnnotation(CustomEntityValidate.class);
                Class<? extends CustomEntityValidator> customValidator = customEntityValidate.validator();
                try {
                    customValidator.newInstance().validate(bean, errors);
                } catch (InstantiationException e) {
                    throw new UnsupportedFieldException("Custom Validation Bean Creation Error: "
                        + bean.getClass().getName());
                }
            }
            FieldValidatorResolver validatorResolver = new FieldValidatorResolver();
            for (Field field : fields) {
                List<FieldValidator> validators = validatorResolver.getValidators(field, bean);
                for (FieldValidator validator : validators) {
                    validator.validate(errors);
                }
            }
        }
    }

    private void getInheritedFields(Class<?> clazz, List<Field> fields) {

        if (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isSynthetic()) {
                    fields.add(field);
                }
            }
            getInheritedFields(clazz.getSuperclass(), fields);
        }
    }
}
