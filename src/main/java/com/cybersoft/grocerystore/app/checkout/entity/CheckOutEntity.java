package com.cybersoft.grocerystore.app.checkout.entity;

import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name="checkout")
public class CheckOutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    @Column(name="address_type")
    private String addressType;

    @Column(name="total_price")
    private float totalPrice;

    @Column(name="fullname")
    private String fullname;

    @Column(name="phonenumber")
    private String phonenumber;

    @Column(name="landmark")
    private String landmark;

    @Column(name="city")
    private String city;

    @Column(name="created_datetime")
    private Date createdDatetime;

    @Column(name="list_id_order")
    private String listIdOrder;

    @OneToMany(mappedBy = "checkOut")
    private List<PaymentEntity> payments;


}
