package com.cybersoft.grocerystore.app.user.controller;


import com.cybersoft.grocerystore.app.user.config.gson.GrantedAuthorityTypeAdaptor;
import com.cybersoft.grocerystore.app.user.config.gson.UserIdentityDTODeserializer;
import com.cybersoft.grocerystore.app.user.dto.UserIdentityDTO;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.repository.UserRepository;
import com.cybersoft.grocerystore.app.user.service.SignupService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.cybersoft.grocerystore.libraries.jwt.JWTHelper;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.cybersoft.grocerystore.app.user.dto.JwtDTO;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(UserIdentityDTO.class, new UserIdentityDTODeserializer())
            .registerTypeAdapter(GrantedAuthority.class, new GrantedAuthorityTypeAdaptor())
            .create();
    final int EXPIRE_TIME = 8 * 60 * 60 * 1000;

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignupService signupService;

    @PostMapping("signin")
    public ResponseEntity<?> signin(HttpServletResponse response, @RequestParam String Username, @RequestParam String Password) throws IOException {

        UsernamePasswordAuthenticationToken principalToken = new UsernamePasswordAuthenticationToken(Username, Password);

        Authentication authentication = authenticationManager.authenticate(principalToken);

        Collection<GrantedAuthority> collectRoles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        String userIndentity = authentication.getPrincipal().toString();
        UserIdentityDTO userIdent = new UserIdentityDTO(collectRoles, userIndentity);
        String userIdentJson  = gson.toJson(userIdent);
        System.out.println("Dev log: " + userIdentJson);

        String userJwtToken = jwtHelper.generateJwsToken(userIdentJson, EXPIRE_TIME);
        JwtDTO jwtAccess = new JwtDTO("accessToken", userJwtToken);
        JwtDTO jwtRefresh = new JwtDTO("refreshToken", "");
        List<JwtDTO> jwtDTOList = List.of(jwtAccess, jwtRefresh);

        BaseResponse baseResponse = new BaseResponse(
                HttpStatus.OK.value(),
                "Login success",
                jwtDTOList
        );

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PostMapping("/decode")
    public ResponseEntity<?> decode(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.split(" ")[1];
        String decodeToken = jwtHelper.getTokenData(token);
        UserIdentityDTO userIdent = gson.fromJson(decodeToken, UserIdentityDTO.class);
        System.out.println("Dev log: Data duoc lay tu JWT: " + decodeToken);
        BaseResponse baseResponse = new BaseResponse(
                HttpStatus.OK.value(),
                "Successfully get data from JWT token",
                userIdent
        );

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        /**
         * TODO: Delete token from database
         * TODO: Check valid token
         */
        System.out.println("Dev log: Token da xoa");

        BaseResponse baseResponse = new BaseResponse(
                HttpStatus.OK.value(),
                "User successfully logged out!",
                "Token deleted!"
        );

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestParam String Username, @RequestParam String Password, @RequestParam String Email, @RequestParam String Phone){
        signupService.signup(Username, Password, Email, Phone, 2);

        BaseResponse baseResponse = new BaseResponse(
                HttpStatus.OK.value(),
                "Successfully signed up with provided information!",
                "Sign up successfully!"
        );

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);


    }

}
