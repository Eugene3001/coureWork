package ua.edu.onu.autoChecking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public static <T> NotFoundException returnNotFoundEntity(T entity, String field) {
        log.error("NOT_FOUND entity: {}, by dto {}", field, entity.toString());
        return new NotFoundException("NOT_FOUND entity");
    }

    public static <T> NotFoundException notFoundWhenDelete(T entity) {
        log.error("NOT_FOUND entity when delete: {}", entity.toString());
        return new NotFoundException("NOT_FOUND entity when delete");
    }

    public static <T> NotFoundException notFoundWhenUpdate(T entity) {
        log.error("NOT_FOUND entity when update: {}", entity.toString());
        return new NotFoundException("NOT_FOUND entity when update");
    }
}
