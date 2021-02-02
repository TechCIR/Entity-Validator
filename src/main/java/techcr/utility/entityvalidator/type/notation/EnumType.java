package techcr.utility.entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumType {
    Class clazz();
    boolean validAny() default false;
    int equalTo() default -1;
    int notEqualTo() default -1;
    String errorDesc() default "Invalid Enum";
    String invalidIndexError() default "Invalid Index";
}
