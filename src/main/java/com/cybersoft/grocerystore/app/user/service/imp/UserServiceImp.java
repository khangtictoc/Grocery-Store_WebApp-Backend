package com.cybersoft.grocerystore.app.user.service.imp;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;

import java.util.List;

public interface UserServiceImp{

    void add(String username, String rawPassword, String email, String phoneNumber, int idRole,String fullname, String avatar);
    //void deleteCategoryById(int id);
    void updateUserById(int id, String username, String rawPassword, String email, String phoneNumber, int idRole,String fullname, String avatar,boolean isActivated);
    UserEntity findUserById(int id);
    List<UserEntity> fillall();
}
