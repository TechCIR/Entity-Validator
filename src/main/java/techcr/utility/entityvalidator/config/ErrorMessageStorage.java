package techcr.utility.entityvalidator.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import techcr.utility.entityvalidator.validator.ValidationError;


public class ErrorMessageStorage {

    public static final String DEFAULT_ERROR_ENTITY = "validate-entity";

    private static ThreadLocal<Map<String, ErrorMessageWrapper>> messageWrapper = ThreadLocal
        .withInitial(HashMap::new);

    public static void addErrorMessageWrapper(String key, List<ValidationError> errors) {
        ErrorMessageWrapper wrapper = new ErrorMessageWrapper(key, errors);
        addErrorMessageWrapper(wrapper);
    }

    public static void addErrorMessageWrapper(ErrorMessageWrapper wrapper) {
        messageWrapper.get().put(wrapper.getKey(), wrapper);
    }

    public static ErrorMessageWrapper getErrorMessageWrapper() {
        return getErrorMessageWrapper(DEFAULT_ERROR_ENTITY);
    }

    public static ErrorMessageWrapper getErrorMessageWrapper(String key) {
        return messageWrapper.get().get(key);
    }

    public static List<ValidationError> getErrorMessages() {
        return getErrorMessages(DEFAULT_ERROR_ENTITY);
    }

    public static List<ValidationError> getErrorMessages(String key) {
        ErrorMessageWrapper wrapper = messageWrapper.get().get(key);
        return null == wrapper ? Collections.emptyList() : wrapper.getValidationErrorList();
    }

    public static void clearErrorMessages() {
        messageWrapper.remove();
    }
}
