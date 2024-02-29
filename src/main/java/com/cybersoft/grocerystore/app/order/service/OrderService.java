package com.cybersoft.grocerystore.app.order.service;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.category.service.imp.CategoryServiceImp;
import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.order.repository.OrderDetailRepository;
import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.app.payment.DTO.PaymentDTO;
import com.cybersoft.grocerystore.app.payment.service.imp.PaymentServiceImp;
import com.cybersoft.grocerystore.app.product.dto.ProductDTO;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.product.service.imp.ProductServiceImp;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import com.cybersoft.grocerystore.app.user.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;
@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    private PaymentServiceImp paymentServiceImp;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;


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

    @Override
    public void add(int idProduct, int idUser, float purchasePrice, int quantity) {
        ProductDTO productDTO = productServiceImp.getProductById(idProduct);

        ProductEntity product = new ProductEntity();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        System.out.println("Kiem tra categoryid = "+productDTO.getCategoryId());
        product.setCategory(categoryServiceImp.findCategoryById(productDTO.getCategoryId()));
        product.setImage(productDTO.getImage());
        product.setAverageRating(productDTO.getAverageRating());
        product.setDescription(productDTO.getDescription());
        product.setOriginalPrice(productDTO.getOriginalPrice());
        product.setDiscountPercent(productDTO.getDiscountPercent());
        product.setQuantity(productDTO.getQuantity());
        product.setActivated(productDTO.isActivated());
        product.setUnit(productDTO.getUnit());
        product.setPrice(productDTO.getPrice());

        UserEntity user = userServiceImp.findUserById(idUser);

        LocalDateTime dateTimeNow = LocalDateTime.now();
        Date dateNow = new Date();
        dateNow = convertToDateViaSqlTimestamp(dateTimeNow);


        OrderDetailEntity order  = new OrderDetailEntity();
        order.setProduct(product);
        order.setUser(user);
        order.setPurchasePrice(purchasePrice);
        order.setQuantity(quantity);
        order.setChecked(false);
        order.setCreatedDatetime(dateNow);
        try{
            orderDetailRepository.save(order);
        }catch (Exception e){
            throw new RuntimeException("Loi save order detail");
        }
    }

    @Override
    public void update(int id, int idProduct, int idUser, float purchasePrice, int quantity, boolean isChecked) {
        ProductDTO productDTO = productServiceImp.getProductById(idProduct);
        ProductEntity product = new ProductEntity();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryServiceImp.findCategoryById(productDTO.getCategoryId()));
        product.setImage(productDTO.getImage());
        product.setAverageRating(productDTO.getAverageRating());
        product.setDescription(productDTO.getDescription());
        product.setOriginalPrice(productDTO.getOriginalPrice());
        product.setDiscountPercent(productDTO.getDiscountPercent());
        product.setQuantity(productDTO.getQuantity());
        product.setActivated(productDTO.isActivated());
        product.setUnit(productDTO.getUnit());
        product.setPrice(productDTO.getPrice());
        UserEntity user = userServiceImp.findUserById(idUser);
        LocalDateTime dateTimeNow = LocalDateTime.now();
        Date dateNow = new Date();
        dateNow = convertToDateViaSqlTimestamp(dateTimeNow);

        OrderDetailEntity orderDetail = getOrderById(id);
        orderDetail.setProduct(product);
        orderDetail.setUser(user);
        orderDetail.setQuantity(quantity);
        orderDetail.setChecked(isChecked);
        orderDetail.setCreatedDatetime(dateNow);
        try{
            orderDetailRepository.save(orderDetail);
        }catch(Exception e ){
            throw new RuntimeException("Loi update order by id "+id);
        }

    }

    @Override
    public void updateIsChecked(int id, boolean isChecked) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        Date dateNow = new Date();
        dateNow = convertToDateViaSqlTimestamp(dateTimeNow);
        OrderDetailEntity orderDetail = getOrderById(id);
        orderDetail.setChecked(isChecked);
        orderDetail.setCreatedDatetime(dateNow);
        try{
            orderDetailRepository.save(orderDetail);
        }catch(Exception e ){
            throw new RuntimeException("Loi updateIsChecked order by id "+id);
        }
    }

    @Override
    public List<Integer> getListIdOrderByIdUser(int idUser, boolean isChecked) {
        UserEntity user = userServiceImp.findUserById(idUser);
        List<Integer> listIdOrder = new ArrayList<>();
        List<OrderDetailEntity> listOrder = orderDetailRepository.findOrderDetailEntityByUser(user);
        for(OrderDetailEntity order : listOrder){
            if (order.isChecked() == isChecked){
                listIdOrder.add(order.getId());
            }
        }

        return listIdOrder;
    }

    @Override
    public float calculateTotalPriceByIdUser(int idUser, boolean isChecked) {
        UserEntity user = userServiceImp.findUserById(idUser);
        float totalPrice = 0;
        List<OrderDetailEntity> listOrder = orderDetailRepository.findOrderDetailEntityByUser(user);
        for(OrderDetailEntity order : listOrder){
            if (order.isChecked() == isChecked){
                totalPrice+=order.getPurchasePrice();
            }
        }
        return totalPrice;
    }


    @Override
    public OrderDetailEntity getOrderById(int id) {

        Optional<OrderDetailEntity> optionalOrder = orderDetailRepository.findById(id);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        throw new RuntimeException("Khong tim thay order by id "+id);

    }





    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

}
