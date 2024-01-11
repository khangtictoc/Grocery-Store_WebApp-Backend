package com.store.shoppy.app.product.entity;


import com.store.shoppy.app.category.entity.CategoryEntity;
import com.store.shoppy.app.order.entity.OrderDetailEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@JsonIgnore
    @ManyToOne()
    @JoinColumn(name="id_category")
    private CategoryEntity category;

    @Column(name="image")
    private String image;

    @Column(name="name")
    private String name;

    @Column(name="unit")
    private String unit;

    @Column(name="price")
    private float price;

    @Column(name="original_price")
    private float originalPrice;

    @Column(name="quantity")
    private int quantity;

    @Column(name="average_rating")
    private double averageRating;

    @Column(name="description")
    private String description;

    @Column(name="discount_percent")
    private float discountPercent;

    @Column(name="is_activated")
    private boolean isActivated = true;

    @OneToMany(mappedBy = "product")
    private List<OrderDetailEntity> orderDetails;


}
