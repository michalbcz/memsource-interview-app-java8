package cz.bernhard.memsource.common.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GenericExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Errors handleValidationExceptions(MethodArgumentNotValidException ex) {
        Errors errorsWrapper = new Errors();

        List<Error> errors = ex.getBindingResult().getAllErrors().stream().map((fieldError) -> {
            String fieldName = ((FieldError) fieldError).getField();
            String errorMessage = fieldError.getDefaultMessage();

            Error error = new Error();
            error.setFieldName(fieldName);
            error.setMessage(errorMessage);

            return error;
        }).collect(Collectors.toList());

        errorsWrapper.setErrors(errors);

        return errorsWrapper;
    }

}
