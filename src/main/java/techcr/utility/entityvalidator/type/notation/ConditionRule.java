package techcr.utility.entityvalidator.type.notation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionRule {
    String rule();
    String errorDesc();
}
