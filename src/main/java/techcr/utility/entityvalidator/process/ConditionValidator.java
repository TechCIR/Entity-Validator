package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlScript;
import org.apache.commons.jexl3.ObjectContext;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.type.notation.ConditionRule;
import techcr.utility.entityvalidator.type.notation.ConditionValidation;
import techcr.utility.entityvalidator.validator.ValidationError;


public class ConditionValidator extends BaseFieldValidator implements FieldValidator {

    public ConditionValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    public ConditionValidator(Object entity) throws IllegalAccessException {
        super(null, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {

        ConditionValidation conditionValidation;
        if (null == field) {
            conditionValidation = instance.getClass().getAnnotation(ConditionValidation.class);
        } else if (null != value) {
            conditionValidation = field.getAnnotation(ConditionValidation.class);
        } else {
            conditionValidation = null;
        }

        if (null != conditionValidation) {
            ConditionRule[] rules = conditionValidation.rules();

            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlContext jexlContext = new ObjectContext<>(jexlEngine, instance);
            //jexlContext.set(conditionValidation.objectAlias(), instance);
            Arrays.stream(rules).forEach(conditionRule -> {
                JexlScript expression = jexlEngine.createScript(conditionRule.rule());
                Boolean isValid = (Boolean) expression.execute(jexlContext);
                if (!isValid) {
                    ValidationError validationError = new ValidationError(conditionRule.errorDesc());
                    String errorDesc = conditionValidation.errorDesc() + " : " + conditionRule.errorDesc();
                    validationError.setErrorDescription(errorDesc);
                    errors.add(validationError);
                }
            });
        }
    }
}
