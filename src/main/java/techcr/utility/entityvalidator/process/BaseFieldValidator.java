package techcr.utility.entityvalidator.process;

import java.lang.reflect.Field;

import techcr.utility.entityvalidator.type.ValidatorUtil;
import techcr.utility.entityvalidator.type.notation.ValidatorFieldDescription;

public abstract class BaseFieldValidator<T> {

    protected Field field;
    protected T instance;
    protected Object value;
    protected String fieldName;


    public BaseFieldValidator(Field field, T entity) throws IllegalAccessException {
        this.field = field;
        this.instance = entity;
        process();
    }

    private void process() throws IllegalAccessException {
        if (null != field) {
            field.setAccessible(true);
            this.value = field.get(instance);
            fieldName = ValidatorUtil.getFieldName(field);
        }
    }


}
