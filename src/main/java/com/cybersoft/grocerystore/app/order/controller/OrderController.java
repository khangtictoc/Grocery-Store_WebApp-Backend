package com.cybersoft.grocerystore.app.order.controller;


import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("add")
    public ResponseEntity<?> addOrderDetail(@RequestParam int idProduct, @RequestParam int idUser, @RequestParam float purchasePrice, @RequestParam int quantity){
        orderServiceImp.add(idProduct,idUser,purchasePrice,quantity);
        return new ResponseEntity<>("Add Order Detail Successfully", HttpStatus.OK);
    }

    @PostMapping("updateischecked")
    public ResponseEntity<?> updateIsCheckedById(@RequestParam int id, @RequestParam boolean isChecked){
        orderServiceImp.updateIsChecked(id,isChecked);
        return new ResponseEntity<>("UpdateIsChecked Order Detail Successfully", HttpStatus.OK);
    }

    @GetMapping("getlistidorder")
    public ResponseEntity<?> getListIdOrder(@RequestParam int idUser, @RequestParam boolean isChecked){
        List<Integer> listIdOrder = orderServiceImp.getListIdOrderByIdUser(idUser,isChecked);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listIdOrder);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @GetMapping("caltotalprice")
    public ResponseEntity<?> calculateTotalPrice(@RequestParam int idUser, @RequestParam boolean isChecked){
        float totalPrice = orderServiceImp.calculateTotalPriceByIdUser(idUser,isChecked);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(totalPrice);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
}
