package com.cybersoft.grocerystore.app.product.service;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.order.service.Imp.OrderServiceImp;
import com.cybersoft.grocerystore.app.product.dto.ProductDTO;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.product.service.imp.FileServiceImp;
import com.cybersoft.grocerystore.app.product.service.imp.ProductServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.grocerystore.app.product.repository.ProductRepository;

import java.util.List;


@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderServiceImp orderServiceImp;

    @Autowired
    HttpServletRequest request;



    // rollbackFor: dùng để custom transactional lắng nghe exception nào
    // dat @transactional ngay truoc service hoac repository
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void save(MultipartFile file, String name, float price, float originalPrice, float discountPercent, int idCategory, String unit, int quantity, double averageRating, String description) {

            fileServiceImp.save(file);
            CategoryEntity category = new CategoryEntity();
            category.setId(idCategory);

            ProductEntity product = new ProductEntity();
            product.setName(name);
            product.setPrice(price);
            product.setOriginalPrice(originalPrice);
            product.setDiscountPercent(discountPercent);
            product.setCategory(category);
            product.setImage(file.getOriginalFilename());
            product.setUnit(unit);
            product.setQuantity(quantity);
            product.setAverageRating(averageRating);
            product.setDescription(description);

        try{
            productRepository.save(product);
        }catch(Exception e){
            throw new RuntimeException("Loi insert product: "+e.getMessage());
        }
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findAllByOrderByQuantity() {
        return productRepository.findAllByOrderByQuantity();
    }
    public List<Integer> findAllGroupByProduct() {
        return productRepository.findAllGroupByProduct();


    @Override
    public ProductDTO getProductById(int id) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if(optionalProductEntity.isPresent()){

            ProductEntity product = optionalProductEntity.get();

            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getId());
            productDTO.setProductName(product.getName());
            productDTO.setProductUnit(product.getUnit());
            productDTO.setProductDescription(product.getDescription());
            productDTO.setProductPrice(product.getPrice());

            if(product.getImage() != null && !product.getImage().isBlank()){
                Resource resource = fileServiceImp.load(product.getImage());
                productDTO.setProductImage( request.getScheme()+"://"+request.getHeader("host") +"/file/"+ resource.getFilename());
            }

            return productDTO;
        }

        throw new RuntimeException("Khong tim thay product");
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> getBestSellerProductsByCategory(int idCategory, int topNumber) {

        List<ProductEntity> productList = new ArrayList<>();
        Set<ProductEntity> uniqueProducts = new HashSet<ProductEntity>();
        //List<Integer> quantityList = new ArrayList<>();

        List<OrderDetailEntity> listCompletedOrder = orderServiceImp.listCompletedOrder();
        System.out.println("kiem tra 1: "+listCompletedOrder.toArray().length);

        Map<ProductEntity, Integer> unsortedMap = new HashMap<>();


        // filtered by idCategory
        for (OrderDetailEntity order:listCompletedOrder ){
            if(order.getProduct().getCategory().getId()==idCategory){
                uniqueProducts.add(order.getProduct());
            }
        }

        System.out.println("kiem tra 2: "+uniqueProducts.size());

        for(ProductEntity product:uniqueProducts){
            int tempQuantity = 0;
            for(OrderDetailEntity order: listCompletedOrder){
                if(order.getProduct().getId() == product.getId()){
                    tempQuantity+=order.getQuantity();
                }
            }
            unsortedMap.put(product,tempQuantity);
        }

        // Convert the entries of the map to a List
        List<Map.Entry<ProductEntity, Integer>> entryList = new ArrayList<>(unsortedMap.entrySet());

        // Sort the List based on the values in ascending order
        entryList.sort(new Comparator<Map.Entry<ProductEntity, Integer>>() {
            @Override
            public int compare(Map.Entry<ProductEntity, Integer> entry1, Map.Entry<ProductEntity, Integer> entry2) {
                // For descending order, swap entry1 and entry2
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });


        // Create a LinkedHashMap to maintain the order of the sorted entries
        //Map<ProductEntity, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<ProductEntity, Integer> entry : entryList) {
            //sortedMap.put(entry.getKey(), entry.getValue());
            productList.add(entry.getKey());
            System.out.println("ten product: "+entry.getKey().getName()+"- sl: "+entry.getValue());
        }

        List<ProductEntity> tempProducts =  productList.subList(0,Math.min(topNumber, productList.size()));
        // Return list productDTO
        List<ProductDTO> listProductDTO = new ArrayList<>();

        for(ProductEntity product: tempProducts){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getId());
            productDTO.setProductName(product.getName());
            productDTO.setProductPrice(product.getPrice());
            productDTO.setProductUnit(product.getUnit());
            productDTO.setProductDescription(product.getDescription());
            productDTO.setCategoryName(product.getCategory().getName());

            if(product.getImage() != null && !product.getImage().isBlank()){
                Resource resource = fileServiceImp.load(product.getImage());
                productDTO.setProductImage( request.getScheme()+"://"+request.getHeader("host") +"/file/"+ resource.getFilename());
            }

            listProductDTO.add(productDTO);
        }

        return listProductDTO;

    }
}
