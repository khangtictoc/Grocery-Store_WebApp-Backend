package com.cybersoft.grocerystore.libraries.exception;


import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(Exception e){

        BaseResponse baseResponse = new BaseResponse(200,"",e.getMessage());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
