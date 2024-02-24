package com.cybersoft.grocerystore.app.checkout.controller;

import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.cybersoft.grocerystore.app.checkout.service.Imp.CheckOutServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("checkout")
public class CheckOutController {

    @Autowired
    CheckOutServiceImp checkOutServiceImp;

    @PostMapping("add-auto-calculate")
    public ResponseEntity<?> addCheckOutAutoCalTotalPrice(@RequestParam int idUser,@RequestParam String addressType,@RequestParam String fullName,@RequestParam String phoneNumber,@RequestParam String landMark,@RequestParam String city){
        checkOutServiceImp.addCheckout(idUser,addressType,fullName,phoneNumber,landMark,city);

        return new ResponseEntity<>("Add checkout auto calculate total price successfully", HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> addCheckOutAutoCalTotalPrice(@RequestParam int idUser,@RequestParam float totalPrice ,@RequestParam String addressType,@RequestParam String fullName,@RequestParam String phoneNumber,@RequestParam String landMark,@RequestParam String city,@RequestParam String listIdOrder){
        checkOutServiceImp.add(idUser,totalPrice,addressType,fullName,phoneNumber,landMark,city,listIdOrder);

        return new ResponseEntity<>("Add checkout successfully", HttpStatus.OK);
    }

    @GetMapping("get-newest")
    public ResponseEntity<?> getNewestCheckOutByIdUser(@RequestParam int idUser){
        CheckOutEntity checkOut =checkOutServiceImp.getcheckout(idUser);
        BaseResponse baseResponse= new BaseResponse();
        baseResponse.setData(checkOut);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }



}
