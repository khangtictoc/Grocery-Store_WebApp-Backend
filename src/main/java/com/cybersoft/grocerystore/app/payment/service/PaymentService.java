package com.cybersoft.grocerystore.app.payment.service;

import com.cybersoft.grocerystore.app.payment.DTO.PaymentDTO;
import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;
import com.cybersoft.grocerystore.app.payment.repository.PaymentRepository;
import com.cybersoft.grocerystore.app.payment.service.imp.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PaymentService implements PaymentServiceImp {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public List<PaymentDTO> completedPayments() {

        List<PaymentEntity> paymentEntityList = paymentRepository.findAllByIsPaidIsTrue();
        List<PaymentDTO> paymentDTOList = new ArrayList<>();

        for (PaymentEntity paymentItem : paymentEntityList){
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setPaymentId(paymentItem.getId());
            paymentDTO.setPaymentIsPaid(paymentItem.isPaid());
            paymentDTO.setPaymentListIdOrder(paymentItem.getCheckOut().getListIdOrder());
            paymentDTOList.add(paymentDTO);
        }
        return paymentDTOList;
    }
}
