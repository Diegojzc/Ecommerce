package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.application.repository.ProductRepository;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Product saveProduct(Product product){
        return productRepository.saveProduct(product);
    }
    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }
}
