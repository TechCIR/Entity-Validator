package techcr.utility.entityvalidator.type.notation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import techcr.utility.entityvalidator.type.NumberCriteriaType;


@Retention(RetentionPolicy.RUNTIME)
public @interface NumberField {
    String number();
    NumberCriteriaType criteriaType() default NumberCriteriaType.EQAUL;
    String errorDesc() default "Invalid Number";
}
