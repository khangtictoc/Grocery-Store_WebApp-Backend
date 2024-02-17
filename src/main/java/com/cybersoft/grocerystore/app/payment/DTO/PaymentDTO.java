package com.cybersoft.grocerystore.app.payment.DTO;


import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import lombok.Data;

@Data
public class PaymentDTO {

    private int paymentId;

    private String paymentListIdOrder;

    private boolean paymentIsPaid;

}
