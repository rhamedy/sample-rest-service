package com.sampleservice.demo.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class StudentValidator {
    /**
     * The following method could  be reused for any entity, to check
     * whether the given Optional is present or not. If not present then it's a 404 error.
     *
     * [Student] with [First Name] [Jacob] does not exist.
     *
     * @param object
     * @param label
     * @param value
     * @param <T>
     */
    public <T> void validate404(Optional<T> object, String label, String value) {
        if (!object.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    object.getClass().getName() + " with " + label + "'" + value + "' does not exist.", null);
        }
    }
}
