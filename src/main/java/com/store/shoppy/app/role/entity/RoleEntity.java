package com.store.shoppy.app.role.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.store.shoppy.app.user.entity.UserEntity;

@Data
@Entity(name="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

}
