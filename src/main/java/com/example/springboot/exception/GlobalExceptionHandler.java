package com.example.springboot.exception;
import com.example.springboot.lib.http.StatusCode;
import com.example.springboot.lib.http.BaseResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DatabaseErrorException.class})
    public ResponseEntity<BaseResponse> handleDatabaseErrorException(DatabaseErrorException exception) {
        return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, exception.getMessage(), null));
    }

    @ExceptionHandler({GeneralErrorExcpetion.class})
    public ResponseEntity<BaseResponse> handleGeneralErrorExcpetion(GeneralErrorExcpetion exception) {
        return BaseResponse.Build(new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR, exception.getMessage(), null));
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<BaseResponse> handleForbiddenException(ForbiddenException exception) {
        return BaseResponse.Build(new BaseResponse(StatusCode.FORBIDDEN, exception.getMessage(), null));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<BaseResponse> handleAccessDeniedException(AccessDeniedException exception) {  
        return BaseResponse.Build(new BaseResponse(StatusCode.UNAUTHORIZED, exception.getMessage(), null));
    }
}
