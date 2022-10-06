package co.com.meli.mutantsMeli.domain.utils;

import co.com.meli.mutantsMeli.config.Generated;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Generated
@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class, InvalidDefinitionException.class})
    public ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Illegal argument exception error";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(MutantException.class)
    public ResponseEntity<Object> mutantResponseEntity(final MutantException e, WebRequest request) {
        String bodyOfResponse = e.getDescription();
        return handleExceptionInternal(e, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        LOGGER.info("Validation error list : "+validationList);
        Error apiErrorVO = new Error(errorMessage,validationList);
        return new ResponseEntity<>(apiErrorVO, status);
    }


}
