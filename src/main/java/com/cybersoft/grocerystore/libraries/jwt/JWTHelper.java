package com.cybersoft.grocerystore.libraries.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTHelper {

    @Value("${keyToken}")
    private String strKey;

    public String generateJWTSecretKey(){
        SecretKey key = Jwts.SIG.HS256.key().build();
        return Encoders.BASE64.encode(key.getEncoded());
    }

    public String generateJwsToken(String data, int expireTime) {

        // Convert to byte-array type object from base64 secret key
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        // Set expire time
        Date date = new Date();
        long futureMiliSecond = date.getTime() + expireTime;
        Date futureDate = new Date(futureMiliSecond);

        // Create JWT token
        return Jwts.builder().subject(data).signWith(key).compact();
    }

    public String getTokenData(String jwsToken) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        String data = "";
        try {
            data = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwsToken).getPayload().getSubject();

        } catch (Exception e) {
            //System.out.println("Lỗi getTokenData JwtHelper " + e.getMessage());
            //don't trust the JWT!

            throw new RuntimeException("Dev log: Parse token fails!");
        }
        return data;
    }
}
