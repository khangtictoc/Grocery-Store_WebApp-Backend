package com.store.shoppy.libraries.jwt.controller;

import com.store.shoppy.libraries.jwt.JWTHelper;
import com.store.shoppy.libraries.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    private final JWTHelper jwtHelper = new JWTHelper();

    @RequestMapping("/get-secret-key")
    public ResponseEntity<?> getJwtSecretKey(){

        BaseResponse baseResponse = new BaseResponse(
                200,
                "Success generate JWT secret key",
                jwtHelper.generateJWTSecretKey()
        );

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
