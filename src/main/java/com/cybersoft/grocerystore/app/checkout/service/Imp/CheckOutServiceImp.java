package com.cybersoft.grocerystore.app.checkout.service.Imp;

import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import org.hibernate.annotations.Check;

public interface CheckOutServiceImp {
    void addCheckout(int idUser,String addressType, String fullName, String phoneNumber, String landMark, String city);
    void add(int idUser,float totalPrice,String addressType, String fullName, String phoneNumber, String landMark, String city,String listIdOrder);
    CheckOutEntity getbyid(int id);
    CheckOutEntity getcheckout(int idUser);

}
