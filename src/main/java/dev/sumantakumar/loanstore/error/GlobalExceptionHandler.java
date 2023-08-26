package dev.sumantakumar.loanstore.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "FAILED");
        body.put("statusCode", -1);
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String error : errors) {
            stringBuilder.append(error);
        }
        if (!errors.isEmpty()) {
            String[] parts = errors.get(0).split(",");
            stringBuilder = new StringBuilder(parts[0]);

        }
        body.put("message", stringBuilder);
        body.put("error", stringBuilder);
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.error("400 Status Code.", ex);
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({InvalidPaymentDateException.class})
    public ResponseEntity<Object> handleReCaptchaInvalid(final InvalidPaymentDateException ex, final WebRequest request) {
        logger.error("400 Status Code", ex);
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}