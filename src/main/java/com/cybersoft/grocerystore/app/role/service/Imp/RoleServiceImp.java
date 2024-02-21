package com.cybersoft.grocerystore.app.role.service.Imp;

import com.cybersoft.grocerystore.app.role.entity.RoleEntity;

import java.util.List;

public interface RoleServiceImp{
     void addRole(String name, String description);
     void deleteRole(int id);
     void updateRole(int id, String name, String description);
     RoleEntity findRoleById(int id);
     List<RoleEntity> findAllRole();

}
