package com.cybersoft.grocerystore.app.card.entity;

import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
