package com.cybersoft.grocerystore.app.user.entity;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.card.entity.UserCardEntity;
import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.cybersoft.grocerystore.app.role.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
@Data
@Entity(name="users")
public class UserEntity {

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

    @OneToMany(mappedBy = "user")
    private List<CheckOutEntity> payments;

    @OneToMany(mappedBy = "user")
    private List<UserCardEntity> userCards;

    @OneToMany(mappedBy = "user")
    private List<OrderDetailEntity> orderDetails;

}
