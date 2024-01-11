package com.store.shoppy.app.checkout.entity;

import com.store.shoppy.app.payment.entity.PaymentEntity;
import com.store.shoppy.app.user.entity.UserEntity;
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

    @OneToMany(mappedBy = "checkOut")
    private List<PaymentEntity> payments;


}
