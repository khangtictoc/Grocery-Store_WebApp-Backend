package com.store.shoppy.app.user.controller;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import com.store.shoppy.libraries.jwt.JWTHelper;
import com.store.shoppy.libraries.payload.response.BaseResponse;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    private Gson gson = new Gson();
    final int EXPIRE_TIME = 8 * 60 * 60 * 1000;

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password){

        UsernamePasswordAuthenticationToken principalToken = new UsernamePasswordAuthenticationToken(email,password);

        Authentication authentication= authenticationManager.authenticate(principalToken);

        Collection<?> collectRoles = authentication.getAuthorities();
        String listRoleJSon  = gson.toJson(collectRoles);
        System.out.println("List role: "+listRoleJSon);


        String userJwtToken = jwtHelper.generateJwsToken(listRoleJSon, EXPIRE_TIME);

        BaseResponse baseResponse = new BaseResponse(
                HttpStatus.OK.value(),
                "Login success",
                userJwtToken
        );

        logger.info("Response: " + baseResponse);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
