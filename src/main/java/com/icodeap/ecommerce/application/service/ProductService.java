package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.application.repository.ProductRepository;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProductService {

    private final ProductRepository productRepository;
    private final UploadFile uploadFile;

    public ProductService(ProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
    }

    public Iterable<Product> getProduct(){
        return productRepository.getProduct();
    }

    public Iterable<Product> getProductByUser(User user){
        return productRepository.getProductByUser(user);
    }
    public Product getProductById(Integer id){
        return productRepository.getProductById(id);
    }


    public Product saveProduct(Product product, MultipartFile multipartFile) throws IOException {
        if(product.getId() == null){
            User user = new User();
            user.setId(1);
            product.setDateCreated(LocalDateTime.now());
            product.setDateUpdated(LocalDateTime.now());
            product.setUser(user);
            product.setImage(uploadFile.upoload(multipartFile));

            return productRepository.saveProduct(product);
        }else{
            Product productDB = productRepository.getProductById(product.getId());
            if (multipartFile.isEmpty()){
                product.setImage(productDB.getImage());

            }else{
                if(!productDB.getImage().equals("default.jpg")){
                    uploadFile.delete(productDB.getImage());
                }
                product.setImage(uploadFile.upoload(multipartFile));
            }
            product.setCode(productDB.getCode());
            product.setUser(productDB.getUser());
            product.setDateCreated(productDB.getDateCreated());
            product.setDateUpdated(LocalDateTime.now());

            return  productRepository.saveProduct(product);

        }

    }
    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }
}
