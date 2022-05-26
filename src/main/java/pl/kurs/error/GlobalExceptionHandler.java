package pl.kurs.error;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.error.constraints.ConstraintErrorHandler;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity(
                e.getFieldErrors()
                        .stream()
                        .map(ve -> new ValidationError(ve.getDefaultMessage(), ve.getField()))
                        .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity(
                new NotFoundDto(e.getName(), e.getKey()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity handleOptimisticEntityLockException(ObjectOptimisticLockingFailureException e) {
        return new ResponseEntity(
                new EntityAlreadyChanged(e.getIdentifier(), "Bad version"),
                HttpStatus.BAD_REQUEST);
    }

    private final Map<String, ConstraintErrorHandler> constraintErrorHandlerMap;

    public GlobalExceptionHandler(Set<ConstraintErrorHandler> handlers) {
        this.constraintErrorHandlerMap = handlers.stream()
                .collect(Collectors.toMap(ConstraintErrorHandler::constraintName, Function.identity()));
    }

    @Value
    class NotFoundDto {
        String entityName;
        String entityKey;
    }

    @Value
    @Builder
    static class ValidationError {
        String error;
        String field;
    }

    @Value
    class EntityAlreadyChanged {
        Object entity;
        String message;
    }


}
