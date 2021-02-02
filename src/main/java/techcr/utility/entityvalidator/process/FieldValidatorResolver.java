package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.notation.ArrayType;
import techcr.utility.entityvalidator.type.notation.CollectionType;
import techcr.utility.entityvalidator.type.notation.ConditionValidation;
import techcr.utility.entityvalidator.type.notation.Constant;
import techcr.utility.entityvalidator.type.notation.CustomCollectionType;
import techcr.utility.entityvalidator.type.notation.CustomValidate;
import techcr.utility.entityvalidator.type.notation.EntityField;
import techcr.utility.entityvalidator.type.notation.EnumType;
import techcr.utility.entityvalidator.type.notation.Length;
import techcr.utility.entityvalidator.type.notation.LengthBetween;
import techcr.utility.entityvalidator.type.notation.Mandatory;
import techcr.utility.entityvalidator.type.notation.NumberFormat;
import techcr.utility.entityvalidator.type.notation.Range;
import techcr.utility.entityvalidator.type.notation.Regex;


public class FieldValidatorResolver {

    public <T> List<FieldValidator> getValidators(Field field, T entity) throws UnsupportedFieldException, IllegalAccessException {
        List<FieldValidator> validators = new ArrayList<>();
        field.setAccessible(true);
        if (field.isAnnotationPresent(EntityField.class)) {
            validators.add(new EntityFieldValidator(field, entity));
        } else {
            if (field.isAnnotationPresent(Mandatory.class)) {
                validators.add(new FieldMandatoryValidator(field, entity));
            }
            if (field.isAnnotationPresent(Length.class)) {
                validators.add(new FieldLengthValidator(field, entity));
            }
            if (field.isAnnotationPresent(LengthBetween.class)) {
                validators.add(new FieldLengthBetweenValidator(field, entity));
            }
            if (field.isAnnotationPresent(Constant.class)) {
                validators.add(new ConstantValidator(field, entity));
            }
            if (field.isAnnotationPresent(Range.class)) {
                validators.add(new FieldRangeValidator(field, entity));
            }
            if (field.isAnnotationPresent(NumberFormat.class)) {
                validators.add(new NumberFormatValidator(field, entity));
            }
            if (field.isAnnotationPresent(Regex.class)) {
                validators.add(new RegexValidator(field, entity));
            }
            if (field.isAnnotationPresent(ConditionValidation.class)) {
                validators.add(new ConditionValidator(field, entity));
            }
            if (field.isAnnotationPresent(EnumType.class)) {
                validators.add(new EnumTypeValidator(field, entity));
            }

            if (field.isAnnotationPresent(CustomValidate.class)) {
                CustomValidate customValidate = field.getAnnotation(CustomValidate.class);
                Class<? extends CustomFieldValidator> validatorClass = customValidate.validator();
                try {
                    validators.add(validatorClass.
                            getDeclaredConstructor(Field.class, Object.class).
                            newInstance(field, entity));
                } catch (Exception e) {
                    throw new UnsupportedFieldException("Cannot Create " + validatorClass.getName() + " Instance");
                }
            }

            if (field.isAnnotationPresent(ArrayType.class)) {
                ArrayType arrayType = field.getAnnotation(ArrayType.class);
                validators.add(new CollectionTypeValidator(field, entity, arrayType));
            } else if (field.isAnnotationPresent(CollectionType.class)) {
                CollectionType collectionType = field.getAnnotation(CollectionType.class);
                validators.add(new CollectionTypeValidator(field, entity, collectionType));
            } else if (field.isAnnotationPresent(CustomCollectionType.class)) {
                CustomCollectionType customCollectionType = field.getAnnotation(CustomCollectionType.class);
                validators.add(new CollectionTypeValidator(field, entity, customCollectionType));
            }
        }
        return validators;
    }
}
