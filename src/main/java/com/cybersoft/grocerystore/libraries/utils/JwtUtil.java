package com.cybersoft.grocerystore.libraries.utils;

import com.cybersoft.grocerystore.libraries.jwt.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Autowired
    private JWTHelper jwtHelper;
    public Boolean validateToken(String token, String checkData) {
        String data = jwtHelper.getTokenData(token);
        if(!data.equals(checkData)) return false;
        return true;
    }
}
