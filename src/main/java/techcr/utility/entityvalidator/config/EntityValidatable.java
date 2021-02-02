package techcr.utility.entityvalidator.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EntityValidatable {

    /**
     * Only if errors found, will add errors to storage. {@link ErrorMessageStorage}
     * If storage enabled, can retrieve error messages from
     *      {@link ErrorMessageStorage#getErrorMessageWrapper(String)} or
     *      {@link ErrorMessageStorage#getErrorMessages(String)}
     * If storage enabled, error handler won't execute.
     * @return
     */
    boolean enableStorage() default false;

    /**
     * If only if errors found and storage ({@link EntityValidatable#enableStorage()}) disable error handler will execute.
     * @return
     */
    Class<EntityValidationErrorHandler> errorHandler() default EntityValidationErrorHandler.class;
}
