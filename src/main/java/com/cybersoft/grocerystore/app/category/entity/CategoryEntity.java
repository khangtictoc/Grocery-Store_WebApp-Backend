package com.cybersoft.grocerystore.app.category.entity;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

}
