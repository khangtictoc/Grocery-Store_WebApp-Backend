package com.cybersoft.grocerystore.app.product.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private int productId;
    private String productName;
    private float productPrice;
    private String productImage;
    private  String productUnit;
    private String productDescription;
    private String categoryName;
}
