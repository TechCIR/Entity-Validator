package techcr.utility.entityvalidator.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ValidatorEntity {
    String value() default ErrorMessageStorage.DEFAULT_ERROR_ENTITY;
}
