package com.cybersoft.grocerystore.app.user.controller;

import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.repository.UserRepository;
import com.cybersoft.grocerystore.app.user.service.imp.UserServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private UserRepository userRepository;


    @CrossOrigin
    @PostMapping("add")
    public ResponseEntity<?> addUser(@RequestParam String username,@RequestParam String rawPassword,@RequestParam String email,@RequestParam String phoneNumber,@RequestParam int idRole,@RequestParam String fullName,@RequestParam String avatar){

        userServiceImp.add(username,rawPassword,email,phoneNumber,idRole,fullName,avatar);

        return new ResponseEntity<>("Add user successfully", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("updatebyid")
    public ResponseEntity<?> updateUserById(@RequestParam int id,@RequestParam String username,@RequestParam String rawPassword,@RequestParam String email,@RequestParam String phoneNumber,@RequestParam int idRole,@RequestParam String fullName,@RequestParam String avatar,@RequestParam boolean isActivated){

        userServiceImp.updateUserById(id,username,rawPassword,email,phoneNumber,idRole,fullName,avatar,isActivated);

        return new ResponseEntity<>("Update user successfully",HttpStatus.OK);
    }


    @GetMapping("getbyid")
    public ResponseEntity<?> getUserById(@RequestParam int id){

        UserEntity user= userServiceImp.findUserById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(user);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("getall")
    public ResponseEntity<?> getall(){

        List<UserEntity> listUser = userServiceImp.fillall();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listUser);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }




}
