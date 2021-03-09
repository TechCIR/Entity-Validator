package techcr.utility.entityvalidator.validator;

import java.util.Objects;

import techcr.utility.entityvalidator.type.ValidatorUtil;

public class ValidationError {

    private String fieldName;
    private String errorDescription;
    private String actualValue;

    public ValidationError(String fieldName) {
        this.fieldName = ValidatorUtil.resolveMessage(fieldName);
    }

    public ValidationError(String fieldName, String errorDescription, String actualValue) {
        this.fieldName = ValidatorUtil.resolveMessage(fieldName);
        this.errorDescription = ValidatorUtil.resolveMessage(errorDescription);
        this.actualValue = actualValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorDescription() {
        return nvl(errorDescription);
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = ValidatorUtil.resolveMessage(errorDescription);
    }

    public String getActualValue() {
        return nvl(actualValue);
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getValidationErrorDescription() {
        return getErrorDescription()
            + " " + ValidatorUtil.resolveValueMessage() + ":"
            + getActualValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return Objects.equals(fieldName, that.fieldName) &&
            Objects.equals(errorDescription, that.errorDescription) &&
            Objects.equals(actualValue, that.actualValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, errorDescription, actualValue);
    }


    @Override
    public String toString() {
        return getFieldName() + " : " + getErrorDescription()
            + " " + ValidatorUtil.resolveValueMessage() + ":"
            + getActualValue();
    }

    private String nvl(String code) {
        return code == null ? "" : code;
    }
}
