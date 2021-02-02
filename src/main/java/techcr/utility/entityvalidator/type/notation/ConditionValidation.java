package techcr.utility.entityvalidator.type.notation;

/*validate based on given conditions*/

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionValidation {
    ConditionRule[] rules();
    String objectAlias() default "o";
    String errorDesc() default "Condition Failed";
}
