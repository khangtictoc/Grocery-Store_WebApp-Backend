package com.cybersoft.grocerystore.app.user.service;

import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.repository.UserRepository;
import com.cybersoft.grocerystore.app.user.service.imp.SignupServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SignupService implements SignupServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean signup(String Username, String Password, String Email, String Phone, int roleId) {
        String encodedPassword = passwordEncoder.encode(Password);

        userRepository.save(new UserEntity(Username, encodedPassword, Email, Phone, roleId));
        return true;
    }
}
