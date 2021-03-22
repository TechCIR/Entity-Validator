package techcr.utility.entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import techcr.utility.entityvalidator.process.CustomEntityValidator;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomEntityValidate {
    Class<? extends CustomEntityValidator> validator();
}
