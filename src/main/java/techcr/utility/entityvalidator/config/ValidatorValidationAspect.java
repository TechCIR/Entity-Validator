package techcr.utility.entityvalidator.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import techcr.utility.entityvalidator.config.EntityValidatable;
import techcr.utility.entityvalidator.config.ErrorMessageWrapper;
import techcr.utility.entityvalidator.config.ValidatorEntity;
import techcr.utility.entityvalidator.config.ValidatorResult;

import techcr.utility.entityvalidator.exception.UnsupportedFieldException;
import techcr.utility.entityvalidator.validator.ValidationError;
import techcr.utility.entityvalidator.validator.Validator;

@Aspect
public class ValidatorValidationAspect {

    @Around(value = "@annotation(techcr.utility.techcr.utility.entityvalidator.config.EntityValidatable)")
    public Object validateSvcEntry(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        EntityValidatable validatable = method.getAnnotation(EntityValidatable.class);

        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Map<String, List<ValidationError>> errorMap = new HashMap<>();
        Map<String, ErrorMessageWrapper> errorMessageWrapperMap = new HashMap<>();
        Validator validator = new Validator();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            Optional<Annotation> optionalValidatorEntity = Arrays.stream(annotations)
                .filter(annotation -> annotation.annotationType() == ValidatorEntity.class).findAny();
            if (optionalValidatorEntity.isPresent()) {
                ValidatorEntity validatorEntity = (ValidatorEntity) optionalValidatorEntity.get();
                Object parameter = args[i];
                try {
                    validator.validate(parameter);
                    List<ValidationError> errors = validator.getValidationErrors();
                    if (!errors.isEmpty()) {
                        errorMap.put(validatorEntity.value(), errors);
                    }

                } catch (IllegalAccessException | UnsupportedFieldException e) {
                    e.printStackTrace();
                    //                LOGGER.error("Validation Lib throw an exception " + e.getMessage(), e);
                }
            } else {
                Optional<Annotation> optionalErrorMessage = Arrays.stream(annotations)
                    .filter(annotation -> annotation.annotationType() == ValidatorResult.class).findAny();
                if (optionalErrorMessage.isPresent()) {
                    ValidatorResult validatorMessage = (ValidatorResult) optionalErrorMessage.get();
                    ErrorMessageWrapper wrapper = new ErrorMessageWrapper(validatorMessage.value());
                    args[i] = wrapper;
                    joinPoint.getArgs();
                    //method.pa
                    errorMessageWrapperMap.put(validatorMessage.value(), wrapper);
                }
            }
        }

        if (!errorMap.isEmpty()) {
            for (Map.Entry<String, ErrorMessageWrapper> entrySet : errorMessageWrapperMap.entrySet()) {
                String key = entrySet.getKey();
                entrySet.getValue().validationErrorList = errorMap.get(key);
            }
            if (validatable.enableStorage()) {
                for (Map.Entry<String, List<ValidationError>> errorEntry : errorMap.entrySet()) {
                    ErrorMessageStorage.addErrorMessageWrapper(errorEntry.getKey(), errorEntry.getValue());
                }
            } else if (validatable.errorHandler() != EntityValidationErrorHandler.class){
                EntityValidationErrorHandler errorHandler = validatable.errorHandler().getDeclaredConstructor()
                    .newInstance();
                List<ErrorMessageWrapper> wrappers = errorMap.entrySet().stream()
                    .map(entry -> new ErrorMessageWrapper(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
                errorHandler.handleValidationErrors(wrappers);
            }
        }

        try {
            return joinPoint.proceed(args);
        } finally {
            ErrorMessageStorage.clearErrorMessages();
        }
    }
}
