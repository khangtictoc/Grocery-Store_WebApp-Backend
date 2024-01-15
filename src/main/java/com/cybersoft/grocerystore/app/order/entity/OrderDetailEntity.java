package com.cybersoft.grocerystore.app.order.entity;


import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name="order_detail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_product")
    private ProductEntity product;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    @Column(name="purchase_price")
    private float purchasePrice;

    @Column(name="quantity")
    private int quantity;

    @Column(name="is_checked")
    private boolean isChecked;

    @Column(name="created_datetime")
    private Date createdDatetime;




}
