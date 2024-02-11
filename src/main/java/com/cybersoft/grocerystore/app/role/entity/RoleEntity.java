package com.cybersoft.grocerystore.app.role.entity;

import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="roles")
public class RoleEntity {
    public RoleEntity() {

    }
    public RoleEntity(int id) {
        this.id = id;
    }

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
