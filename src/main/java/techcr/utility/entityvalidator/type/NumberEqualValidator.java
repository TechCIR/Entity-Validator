package techcr.utility.entityvalidator.type;

public class NumberEqualValidator implements EqualValidator<Number> {

    @Override
    public boolean isEqual(String constantValue, Number actualValue) {
        if (actualValue == null ) {
            return false;
        }
        Double value = (constantValue == null || constantValue.length() == 0) ? 0.0 : Double.parseDouble(constantValue);
        return value.equals(actualValue.doubleValue());
    }
}
