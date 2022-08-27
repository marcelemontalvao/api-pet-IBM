package br.com.ibm.pet.handlers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class HandlerOrderService {

    @ExceptionHandler(value = ResponseStatusException.class)
    protected ResponseEntity<MensagemErro> handlerOrdemJaCadastrada(ResponseStatusException exception) {
        if (exception.getStatus() == HttpStatus.OK){
            return ResponseEntity.status(HttpStatus.OK).body(new MensagemErro("Ordem já existente."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro("Ordem não encontrada."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErro> handlerValidationExceptions(MethodArgumentNotValidException e,
                                                                   HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessage = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(ex -> {
            String msgError = "O campo '" + ex.getField() + "' " + ex.getDefaultMessage();
            errorMessage.add(msgError);
        });
        return ResponseEntity.status(status).body(new MensagemErro(errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MensagemErro> handlerHttpValidationExceptions(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Invalid information";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) e.getCause();
            errorMessage = cause.getCause().getMessage();
        }
        return ResponseEntity.status(status).body(new MensagemErro(errorMessage));
    }
}
