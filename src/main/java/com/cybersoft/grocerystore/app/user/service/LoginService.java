package com.cybersoft.grocerystore.app.user.service;

import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.repository.UserRepository;
import com.cybersoft.grocerystore.app.user.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserEntity checkLogin(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity!=null){
            String savedPassword = userEntity.getPassword();
            if(passwordEncoder.matches(password,savedPassword)){
                return userEntity;
            }
        }
        return null;
    }
}
