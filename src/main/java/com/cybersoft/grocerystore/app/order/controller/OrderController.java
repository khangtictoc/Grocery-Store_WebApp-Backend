package com.cybersoft.grocerystore.app.order.controller;


import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImp orderServiceImp;
    @GetMapping("completed-orders")
    public ResponseEntity<?> listCompletedOrder(){

        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setData(orderServiceImp.listCompletedOrder());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }


}
