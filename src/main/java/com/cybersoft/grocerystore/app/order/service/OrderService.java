package com.cybersoft.grocerystore.app.order.service;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.order.repository.OrderDetailRepository;
import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.app.payment.DTO.PaymentDTO;
import com.cybersoft.grocerystore.app.payment.service.imp.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    private PaymentServiceImp paymentServiceImp;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailEntity> listCompletedOrder() {

        List<PaymentDTO> paymentDTOList = paymentServiceImp.completedPayments();
        List<Integer> listIdOrder = new ArrayList<>();

        for(PaymentDTO item : paymentDTOList){
            String tempList = item.getPaymentListIdOrder();
            String[] tmpArray = tempList.split(",");
            for(String i:tmpArray){
                listIdOrder.add(Integer.parseInt(i));
            }
        }

        List<OrderDetailEntity> listOrder = new ArrayList<>();

        for(int id : listIdOrder){

            Optional<OrderDetailEntity> optionalOrder =orderDetailRepository.findById(id);
            if(optionalOrder.isPresent()){
                listOrder.add(optionalOrder.get());
            }
        }

        return listOrder;
    }


}
