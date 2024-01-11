package com.store.shoppy.app.user.service.imp;

import com.store.shoppy.app.user.entity.UserEntity;

public interface LoginServiceImp {

    UserEntity checkLogin (String email, String password);

}
