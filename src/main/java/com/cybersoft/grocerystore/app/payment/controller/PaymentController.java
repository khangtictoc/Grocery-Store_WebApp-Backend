package com.cybersoft.grocerystore.app.payment.controller;


import com.cybersoft.grocerystore.app.payment.DTO.PaymentDTO;
import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;
import com.cybersoft.grocerystore.app.payment.service.PaymentService;
import com.cybersoft.grocerystore.app.payment.service.imp.PaymentServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentServiceImp paymentServiceImp;

    @GetMapping("")
    public ResponseEntity<?> completedPaymentsChecking(){

        List<PaymentDTO> listPaymentDTO = paymentServiceImp.completedPayments();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listPaymentDTO);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


}
