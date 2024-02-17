package com.cybersoft.grocerystore.app.payment.entity;

import com.cybersoft.grocerystore.app.card.entity.UserCardEntity;
import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name="payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name="id_checkout")
    private CheckOutEntity checkOut;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_user_card")
    private UserCardEntity userCard;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="is_paid")
    private boolean isPaid;

    @Column(name="created_datetime")
    private Date createdDatetime;
}
