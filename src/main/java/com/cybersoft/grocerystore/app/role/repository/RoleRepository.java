package com.cybersoft.grocerystore.app.role.repository;

import com.cybersoft.grocerystore.app.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
}
