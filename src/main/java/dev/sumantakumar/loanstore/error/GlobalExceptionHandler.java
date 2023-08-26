package dev.sumantakumar.loanstore.error;

import com.iserveu.web.util.GenericResponse;
import com.iserveu.web.util.Utility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messages;


    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request){
        Map<String, Object> body = new HashMap<>();
        body.put("status", "FAILED");
        body.put("statusCode", -1);
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for(String error : errors){
            stringBuilder.append(error);
        }
        String statusDesc = String.valueOf(errors);
        //body.put("statusDesc",statusDesc.substring(1,statusDesc.length()-1));

        //
        if(!errors.isEmpty()){
            String[] parts = errors.get(0).split(",");
            stringBuilder = new StringBuilder(parts[0]);

        }
        body.put("message",stringBuilder);
        body.put("error",stringBuilder);
        //final GenericResponse bodyOfResponse = new GenericResponse(stringBuilder.toString(), stringBuilder.toString());
        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();
        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();
        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid Request Body : " + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }*/

    @ExceptionHandler({ ReCaptchaInvalidException.class })
    public ResponseEntity<Object> handleReCaptchaInvalid(final RuntimeException ex, final WebRequest request) {
        logger.error("400 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.invalidReCaptcha", null, request.getLocale()), "InvalidReCaptcha");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 404
    @org.springframework.web.bind.annotation.ExceptionHandler({ UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(final UserNotFoundException ex, final WebRequest request) {
        logger.error("404 Status Code", ex);
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.userNotFound", null, request.getLocale()), "UserNotFound");
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({UserRoleNotFoundException.class})
    public ResponseEntity<Object> handleUserRoleNotFound(final UserRoleNotFoundException ex, final WebRequest request) {
        logger.error("404 Status Code", ex);
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.userNotFound", null, request.getLocale()), "UserNotFound");
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409
    @org.springframework.web.bind.annotation.ExceptionHandler({ UserAlreadyExistException.class })
    public ResponseEntity<Object> handleUserAlreadyExist(final UserAlreadyExistException ex, final WebRequest request) {
        logger.error("409 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({ BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(final BadRequestException ex, final WebRequest request) {
        logger.error("400 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), null);//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.invalidOldPassword", null, request.getLocale()), "InvalidOldPassword");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleCustomException(final CustomException ex, final WebRequest request) {
        logger.error("400 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), null);//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.invalidOldPassword", null, request.getLocale()), "InvalidOldPassword");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ BankPropertiesNotFoundException.class })
    public ResponseEntity<Object> handleBankPropertiesNotFoundException(final BankPropertiesNotFoundException ex, final WebRequest request) {
        logger.error("417 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), null);//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.invalidOldPassword", null, request.getLocale()), "InvalidOldPassword");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ ExpiredJwtException.class, JwtExpiredException.class })
    public ResponseEntity<Object> handleExpiredJwtException(final RuntimeException ex, final WebRequest request) {
        logger.error("409 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 500
/*    @ExceptionHandler({ MailAuthenticationException.class })
    public ResponseEntity<Object> handleMail(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), null);//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.email.config.error", null, request.getLocale()), "MailError");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @org.springframework.web.bind.annotation.ExceptionHandler({ ReCaptchaUnavailableException.class })
    public ResponseEntity<Object> handleReCaptchaUnavailable(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.unavailableReCaptcha", null, request.getLocale()), "InvalidReCaptcha");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ UnAuthorizedAccessException.class })
    public ResponseEntity<Object> handleUnAuthorizedAccessException(final UnAuthorizedAccessException ex, final WebRequest request) {
        logger.error("401 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({ MobileAlreadyExistException.class })
    public ResponseEntity<Object> handleUserAlreadyExistWithMobile(final MobileAlreadyExistException ex, final WebRequest request) {
        logger.error("409 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ EmailAlreadyExistException.class })
    public ResponseEntity<Object> handleUserAlreadyExistWithEmail(final EmailAlreadyExistException ex, final WebRequest request) {
        logger.error("409 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), ex.getMessage());//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.error("ConstraintViolationException => 400 Status Code", ex);
        String message = ex.getMessage();
        String[] split = message.split(":|,");
        StringBuilder data= new StringBuilder();
        for(int i = 1; i<split.length;i++) {
            if(i % 2 != 0) {
                data.append(split[i]).append(".");
            }
        }
        final GenericResponse bodyOfResponse = new GenericResponse(data.toString().trim(), data.toString().trim());//ex "message.regError"
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }




    @org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.error", null, request.getLocale()), "InternalError");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }




}