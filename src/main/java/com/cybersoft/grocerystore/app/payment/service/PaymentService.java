package com.cybersoft.grocerystore.app.payment.service;

import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.cybersoft.grocerystore.app.checkout.service.Imp.CheckOutServiceImp;
import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.app.payment.DTO.PaymentDTO;
import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;
import com.cybersoft.grocerystore.app.payment.repository.PaymentRepository;
import com.cybersoft.grocerystore.app.payment.service.imp.PaymentServiceImp;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PaymentService implements PaymentServiceImp {
    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private CheckOutServiceImp checkOutServiceImp;
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

    @Override
    public void add(int idCheckOut, String paymentMethod, boolean isPaid) {


        CheckOutEntity checkOut = checkOutServiceImp.getbyid(idCheckOut);

        Date datetimeNow = convertToDateViaSqlTimestamp();

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setCheckOut(checkOut);
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setPaid(isPaid);
        paymentEntity.setCreatedDatetime(datetimeNow);

        try{
            paymentRepository.save(paymentEntity);
        }catch (Exception e){
            throw new RuntimeException("Loi add payment");
        }

    }

    @Override
    public void addByIdUser(int idUser, String paymentMethod, boolean isPaid) {

        Date datetimeNow = convertToDateViaSqlTimestamp();
        CheckOutEntity checkOut = checkOutServiceImp.getcheckout(idUser);
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setCheckOut(checkOut);
        paymentEntity.setPaymentMethod(paymentMethod);
        paymentEntity.setPaid(isPaid);
        paymentEntity.setCreatedDatetime(datetimeNow);
        try{
            paymentRepository.save(paymentEntity);
        }catch (Exception e){
            throw new RuntimeException("Loi add by id user payment");
        }
    }

    public Date convertToDateViaSqlTimestamp() {

        LocalDateTime localDateTime = LocalDateTime.now();

        return java.sql.Timestamp.valueOf(localDateTime);
    }

}
