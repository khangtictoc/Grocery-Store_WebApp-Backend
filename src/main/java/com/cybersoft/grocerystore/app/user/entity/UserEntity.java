package com.cybersoft.grocerystore.app.user.entity;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.card.entity.UserCardEntity;
import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.cybersoft.grocerystore.app.role.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
@Data
@Entity(name="users")
public class UserEntity {
    public UserEntity() {

    }
    public UserEntity(String username,  String password, String email, String phoneNumber, int idRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = new RoleEntity(idRole);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    //@JsonIgnore
    @ManyToOne()
    @JoinColumn(name="id_role")
    private RoleEntity role;

    @Column(name="fullname")
    private String fullname;

    @Column(name="avatar")
    private String avatar;

    @Column(name="phonenumber")
    private String phoneNumber;

    @Column(name="is_activated")
    private boolean isActivated;

    @Column(name="created_datetime")
    private Date createdDatetime;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CheckOutEntity> payments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserCardEntity> userCards;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<OrderDetailEntity> orderDetails;


}
