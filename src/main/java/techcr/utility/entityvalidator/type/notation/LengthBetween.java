package techcr.utility.entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import techcr.utility.entityvalidator.type.NumberCriteriaType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LengthBetween {
    int minLength();
    int maxLength();
    NumberCriteriaType minLengthCriteria () default NumberCriteriaType.GREATER_THAN_OR_EQUAL;
    NumberCriteriaType maxLengthCriteria () default NumberCriteriaType.LESS_THAN_OR_EQUAL;
    String errorDesc() default "Invalid Between Length";
}
