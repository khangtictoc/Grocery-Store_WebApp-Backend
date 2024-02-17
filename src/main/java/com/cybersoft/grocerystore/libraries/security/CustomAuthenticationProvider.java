package com.cybersoft.grocerystore.libraries.security;


import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.service.imp.LoginServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginServiceImp loginServiceImp;

    private Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserEntity userEntity = loginServiceImp.checkLogin(email,password);
        System.out.println("Kiem tra customAuthentication provider");
        logger.info("Kiem tra: "+email);

        if( userEntity!= null){

            List<GrantedAuthority> listRoles = new ArrayList<>();
            // SimpleGrantedAuthority la lop con cua GrantedAuthority
            SimpleGrantedAuthority role = new SimpleGrantedAuthority(userEntity.getRole().getName());
            listRoles.add(role);
            String principal = userEntity.getEmail();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,"",listRoles);
            return authenticationToken;
        }

        System.out.println("Authentication has failed");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
