package techcr.utility.entityvalidator.config;

import java.util.Collections;
import java.util.List;

import techcr.utility.entityvalidator.validator.ValidationError;


public class ErrorMessageWrapper {

    String key;
    List<ValidationError> validationErrorList;

    public ErrorMessageWrapper() {
    }

    public ErrorMessageWrapper(String key) {
        this.key = key;
    }

    public ErrorMessageWrapper(String key, List<ValidationError> validationErrorList) {
        this.key = key;
        this.validationErrorList = validationErrorList;
    }

    public String getKey() {
        return key;
    }

    public List<ValidationError> getValidationErrorList() {
        return validationErrorList == null ? Collections.EMPTY_LIST : validationErrorList;
    }
}
