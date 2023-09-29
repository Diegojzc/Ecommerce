package com.icodeap.ecommerce.infrastructure.adapter;

import com.icodeap.ecommerce.application.repository.OrderProductRepository;
import com.icodeap.ecommerce.domain.Order;
import com.icodeap.ecommerce.domain.OrderProduct;
import com.icodeap.ecommerce.infrastructure.mapper.OrderMapper;
import com.icodeap.ecommerce.infrastructure.mapper.OrderproductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderCrudProductRepository orderCrudProductRepository;
    private final OrderMapper orderMapper;
   private final OrderproductMapper orderProductMapper;

    public OrderProductRepositoryImpl(OrderCrudProductRepository orderCrudProductRepository, OrderMapper orderMapper, OrderproductMapper orderProductMapper) {
        this.orderCrudProductRepository = orderCrudProductRepository;
        this.orderMapper = orderMapper;
        this.orderProductMapper = orderProductMapper;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return orderProductMapper.toOrderProduct(orderCrudProductRepository.save(orderProductMapper.toOrderProductEntity(orderProduct)));
    }

    @Override
    public Iterable<OrderProduct> getOrderProducts() {
        return orderProductMapper.toOrderProducts(orderCrudProductRepository.findAll());

    }


    @Override
    public List<OrderProduct> getOrdersProductByOrder(Order order) {
        return orderProductMapper.toOrderProductsList(orderCrudProductRepository.findByPkOrderEntity(orderMapper.toOrderEntity(order)));
    }
}





