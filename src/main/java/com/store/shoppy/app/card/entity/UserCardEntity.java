package com.store.shoppy.app.card.entity;

import com.store.shoppy.app.payment.entity.PaymentEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.store.shoppy.app.user.entity.UserEntity;

@Data
@Entity(name="user_card")
public class UserCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    @Column(name="name_card")
    private String nameCard;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="cvv")
    private String cvv;

    @Column(name="cardholder_name")
    private String cardHolderName;

    @Column(name="valid_thru")
    private String validThru;

    @OneToMany(mappedBy = "userCard")
    private List<PaymentEntity> payments;



}
