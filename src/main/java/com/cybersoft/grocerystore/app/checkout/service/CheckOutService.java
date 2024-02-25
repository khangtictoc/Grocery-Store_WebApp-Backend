package com.cybersoft.grocerystore.app.checkout.service;

import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.cybersoft.grocerystore.app.checkout.repository.CheckOutRepository;
import com.cybersoft.grocerystore.app.checkout.service.Imp.CheckOutServiceImp;
import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckOutService implements CheckOutServiceImp {
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    OrderServiceImp orderServiceImp;
    @Autowired
    private CheckOutRepository checkOutRepository;

    @Override
    public void addCheckout(int idUser, String addressType, String fullName, String phoneNumber, String landMark, String city) {
        UserEntity user = userServiceImp.findUserById(idUser);

        float totalPrice = orderServiceImp.calculateTotalPriceByIdUser(idUser,false);

        List<Integer> listIdOrder = orderServiceImp.getListIdOrderByIdUser(idUser,false);
        String idOrderStrList = listIdOrder.toString();

        LocalDateTime localDateTime = LocalDateTime.now();
        Date datetimeNow = new Date();
        datetimeNow = convertToDateViaSqlTimestamp(localDateTime);

        CheckOutEntity checkOut = new CheckOutEntity();
        checkOut.setUser(user);
        checkOut.setAddressType(addressType);
        checkOut.setFullname(fullName);
        checkOut.setPhonenumber(phoneNumber);
        checkOut.setLandmark(landMark);
        checkOut.setCity(city);
        checkOut.setTotalPrice(totalPrice);
        checkOut.setListIdOrder(idOrderStrList);
        checkOut.setCreatedDatetime(datetimeNow);

        try{
            checkOutRepository.save(checkOut);
        }catch (Exception e){
            throw new RuntimeException("Loi addCheckout checkout");
        }

    }

    @Override
    public void add(int idUser, float totalPrice, String addressType, String fullName, String phoneNumber, String landMark, String city, String listIdOrder) {
        UserEntity user = userServiceImp.findUserById(idUser);

        LocalDateTime localDateTime = LocalDateTime.now();
        Date datetimeNow = new Date();
        datetimeNow = convertToDateViaSqlTimestamp(localDateTime);

        CheckOutEntity checkOut = new CheckOutEntity();
        checkOut.setUser(user);
        checkOut.setAddressType(addressType);
        checkOut.setFullname(fullName);
        checkOut.setPhonenumber(phoneNumber);
        checkOut.setLandmark(landMark);
        checkOut.setCity(city);
        checkOut.setTotalPrice(totalPrice);
        checkOut.setListIdOrder(listIdOrder);
        checkOut.setCreatedDatetime(datetimeNow);

        try{
            checkOutRepository.save(checkOut);
        }catch (Exception e){
            throw new RuntimeException("Loi add checkout");
        }
    }

    @Override
    public CheckOutEntity getbyid(int id) {
        Optional<CheckOutEntity> optionalCheckOut  = checkOutRepository.findById(id);
        if(optionalCheckOut.isPresent()){
            return optionalCheckOut.get();
        }
        throw new RuntimeException("Khong tim thay checkout by id "+id);
    }

    @Override
    public CheckOutEntity getcheckout(int idUser) {

        UserEntity user = userServiceImp.findUserById(idUser);
        try{
            return checkOutRepository.findFirstByUserOrderByCreatedDatetimeDesc(user);
        }catch (Exception e){
            throw new RuntimeException("Khong tim thay checkout by id user: "+idUser);
        }
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
