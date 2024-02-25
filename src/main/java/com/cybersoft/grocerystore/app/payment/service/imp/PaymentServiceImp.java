package com.cybersoft.grocerystore.app.payment.service.imp;

import com.cybersoft.grocerystore.app.payment.DTO.PaymentDTO;
import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;

import java.util.List;

public interface PaymentServiceImp {

    List<PaymentDTO> completedPayments();
    void add(int idCheckOut,String paymentMethod,boolean isPaid);
    void addByIdUser(int idUser,String paymentMethod,boolean isPaid);


}
