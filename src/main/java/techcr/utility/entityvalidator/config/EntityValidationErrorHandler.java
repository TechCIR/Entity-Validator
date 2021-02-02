package techcr.utility.entityvalidator.config;

import java.util.List;

public interface EntityValidationErrorHandler {
    void handleValidationErrors(List<ErrorMessageWrapper> wrappers);
}

