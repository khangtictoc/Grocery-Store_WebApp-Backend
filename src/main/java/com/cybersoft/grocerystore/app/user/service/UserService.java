package com.cybersoft.grocerystore.app.user.service;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.role.entity.RoleEntity;
import com.cybersoft.grocerystore.app.role.service.Imp.RoleServiceImp;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.repository.UserRepository;
import com.cybersoft.grocerystore.app.user.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleServiceImp roleServiceImp;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void add(String username, String rawPassword, String email, String phoneNumber, int idRole, String fullName, String avatar) {

        RoleEntity role = roleServiceImp.findRoleById(idRole);
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setFullname(fullName);
        user.setAvatar(avatar);

        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Loi add user");
        }

    }

    @Override
    public void updateUserById(int id, String username, String rawPassword, String email, String phoneNumber, int idRole, String fullName, String avatar, boolean isActivated) {

        RoleEntity role = roleServiceImp.findRoleById(idRole);
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        UserEntity user = findUserById(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setFullname(fullName);
        user.setAvatar(avatar);
        user.setActivated(isActivated);
        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Loi update user");
        }

    }

    @Override
    public UserEntity findUserById(int id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new RuntimeException("Khong tim thay user by id "+id);
    }


    @Override
    public List<UserEntity> fillall() {
        return userRepository.findAll();
    }
}
