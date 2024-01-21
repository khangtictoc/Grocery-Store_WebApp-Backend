package com.cybersoft.grocerystore.libraries.filter;

import com.cybersoft.grocerystore.app.user.config.gson.GrantedAuthorityTypeAdaptor;
import com.cybersoft.grocerystore.app.user.config.gson.UserIdentityDTODeserializer;
import com.cybersoft.grocerystore.app.user.dto.UserIdentityDTO;
import com.cybersoft.grocerystore.libraries.jwt.JWTHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class JwtFilter extends OncePerRequestFilter {
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(UserIdentityDTO.class, new UserIdentityDTODeserializer())
            .registerTypeAdapter(GrantedAuthority.class, new GrantedAuthorityTypeAdaptor())
            .create();

    @Autowired
    private JWTHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        Optional<String> tokenOptional = Optional.ofNullable(bearerToken);

        if(tokenOptional.isPresent()) {
            //var token1 = tokenOptional.stream().map(data -> data.replace("Bearer ",""));
            String token = tokenOptional.get().replace("Bearer ","");
            if(!token.isEmpty()){
                String data = jwtHelper.getTokenData(token);
                //Tạo ra custom type để Gson hỗ trợ parse json kiểu List<Object>
                //Type listCustomType = new TypeToken<List<SimpleGrantedAuthority>>() {}.getType();
                Type customType = new TypeToken<UserIdentityDTO>() {}.getType();
                UserIdentityDTO userIdent = gson.fromJson(data, customType);

                System.out.println("data not null "+data);
                System.out.println("user " + userIdent.getUserIndentity());
                System.out.println("role " + userIdent.getRoles());
                if(data!=null){
                    // Tạo ra authentication với username và password rỗng -> để bypass qua hệ thống security, không cần phải tra cứu, chứng thực lại thông tin
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userIdent.getUserIndentity(),"",userIdent.getRoles());
                    // Tạo ra security Context  cho request này
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    // Cấp  securityContext với SecurityContextHolder là authenticationToken
                    securityContext.setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
