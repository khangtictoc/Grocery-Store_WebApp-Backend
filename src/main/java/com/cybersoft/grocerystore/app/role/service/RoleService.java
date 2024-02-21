package com.cybersoft.grocerystore.app.role.service;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.role.entity.RoleEntity;
import com.cybersoft.grocerystore.app.role.repository.RoleRepository;
import com.cybersoft.grocerystore.app.role.service.Imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleServiceImp {
    @Autowired
    private RoleRepository roleRepository;
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void addRole(String name, String description) {
        RoleEntity role = new RoleEntity();
        role.setName(name);
        role.setDescription(description);
        try{
            roleRepository.save(role);
        }catch (Exception e){
            throw new RuntimeException("Loi insert role: "+e.getMessage());
        }
    }

    @Override
    public void deleteRole(int id) {
        RoleEntity role = findRoleById(id);
        try{
            roleRepository.delete(role);
        }catch (Exception e){
            throw new RuntimeException("Loi delete role: "+e.getMessage());
        }
    }
    @Override
    public void updateRole(int id, String newName, String newDescription) {
        RoleEntity role = findRoleById(id);
        role.setName(newName);
        role.setDescription(newDescription);
        try{
            roleRepository.save(role);
        }catch (Exception e){
            throw new RuntimeException("Loi update role: "+e.getMessage());
        }
    }

    @Override
    public RoleEntity findRoleById(int id) {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(id);
        if(optionalRoleEntity.isPresent()) {
            return optionalRoleEntity.get();
        }
        throw new RuntimeException("Khong tim thay role");
    }

    @Override
    public List<RoleEntity> findAllRole() {
        return roleRepository.findAll();
    }

}
