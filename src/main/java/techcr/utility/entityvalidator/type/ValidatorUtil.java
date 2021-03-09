package techcr.utility.entityvalidator.type;

import java.lang.reflect.Field;

import techcr.utility.entityvalidator.config.MessageResolver;
import techcr.utility.entityvalidator.type.notation.ValidatorFieldDescription;


public class ValidatorUtil {

    private static MessageResolver messageResolver = key -> key;
    public static final String MESSAGE_KEY_PREFIX = "KEY::";
    public static final String VALUE_MESSAGE = "value";

    public static void configureResolver(MessageResolver messageResolver) {
        ValidatorUtil.messageResolver = messageResolver;
    }

    public static String resolveMessage(String messageKey) {
        if (messageKey != null && messageKey.startsWith(MESSAGE_KEY_PREFIX)) {
            String key = messageKey.substring(MESSAGE_KEY_PREFIX.length());
            return messageResolver.resolveMessage(key);
        }
        return messageKey;
    }

    public static String resolveValueMessage() {
        String valueWithii8 = messageResolver.resolveMessage("VALUE");
        return valueWithii8 == null || valueWithii8.isEmpty() ? VALUE_MESSAGE : valueWithii8;
    }

    public static String getFieldName(Field field) {
        field.setAccessible(true);
        if (field.isAnnotationPresent(ValidatorFieldDescription.class)) {
            ValidatorFieldDescription description = field.getAnnotation(ValidatorFieldDescription.class);
            return description.fieldDesc();
        }
        return field.getName();
    }


}
