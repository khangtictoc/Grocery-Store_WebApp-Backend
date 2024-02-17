package com.cybersoft.grocerystore.app.user.service.imp;

import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface SignupServiceImp {
    public boolean signup(String Username, String Password, String Email, String Phone, int roleId);
}
