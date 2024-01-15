package com.cybersoft.grocerystore.app.user.service.imp;

import com.cybersoft.grocerystore.app.user.entity.UserEntity;

public interface LoginServiceImp {

    UserEntity checkLogin (String email, String password);

}
