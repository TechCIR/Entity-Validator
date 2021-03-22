package techcr.utility.entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import techcr.utility.entityvalidator.type.NumberCriteriaType;

/**
 * Length validation.
 * Not support to double and float.
 * Can define condition by LengthCriteriaType
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {
    int length();
    NumberCriteriaType lengthCriteriaType() default NumberCriteriaType.EQAUL;
    String errorDesc() default "Invalid Length";

}
