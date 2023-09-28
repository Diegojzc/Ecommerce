package com.icodeap.ecommerce.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;
    List<OrderProduct> orderProducts;
    private User user;

    public void AddOrdersProduct(List<OrderProduct> orderProducts){
        this.setOrderProducts(orderProducts);
    }
    public BigDecimal getTotalOrderPrice(){
        return getOrderProducts().stream().map(
                        OrderProduct::getTotalPrice).
                        reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
