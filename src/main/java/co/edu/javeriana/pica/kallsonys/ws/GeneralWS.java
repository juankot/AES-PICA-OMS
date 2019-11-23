package co.edu.javeriana.pica.kallsonys.ws;

import co.edu.javeriana.pica.kallsonys.dto.GenericResponse;
import co.edu.javeriana.pica.kallsonys.exceptions.KallSonysException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GeneralWS {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        StringBuilder errorsSb = new StringBuilder();
        errors.forEach((errorK, errorV) -> {
            errorsSb.append(errorV + "; ");
        });

        errorsSb.deleteCharAt(errorsSb.length()-1);
        return new GenericResponse(2, errorsSb.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public GenericResponse handleArgumentValidationExceptions(MethodArgumentTypeMismatchException ex) {
        return new GenericResponse(
                2,
                "El PathParam '" + ex.getName() +
                        "' con valor '" + ex.getValue() +
                        "' debe ser de tipo " + ex.getRequiredType().getName());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(KallSonysException.class)
    public GenericResponse badRequest(KallSonysException ex) {
        return new GenericResponse(3, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericResponse handleOtherExceptions(Exception ex) {
        ex.printStackTrace();
        return new GenericResponse(1, ex.getMessage());
    }

}
