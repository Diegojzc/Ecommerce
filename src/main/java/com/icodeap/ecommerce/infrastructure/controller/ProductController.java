package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String create(){
        return "admin/products/create";
    }

    @PostMapping("/save-product")
    public String saveProduct(Product product, @RequestParam("img") MultipartFile multipartFile,HttpSession httpSession) throws IOException {
        log.info("nombre de producto: {}",product.toString());
        productService.saveProduct(product, multipartFile,httpSession);
        return "redirect:/admin";
        //return "admin/products/create";
    }

    @GetMapping("/show")
    public String showProduct(Model model,HttpSession httpSession){
        log.info("Id user desde la sesion:{}",httpSession.getAttribute("iduser").toString());

        User user= new User();
        user.setId(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        Iterable<Product> products = productService.getProductByUser(user);
        model.addAttribute("products",products);
        return "admin/products/show";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model){
        Product product = productService.getProductById(id);
        log.info("Product obtenido {}", product);
        model.addAttribute("product",product);

        return "admin/products/edit";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteById(id);
        return "redirect:/admin/products/show";
    }
}
