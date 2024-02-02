package com.cybersoft.grocerystore.app.product.dto;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private int id;
    private int categoryId;
    private String image;
    private String name;
    private String unit;
    private float price;
    private float originalPrice;
    private int quantity;
    private double averageRating;
    private String description;
    private float discountPercent;
    private boolean isActivated ;

}
