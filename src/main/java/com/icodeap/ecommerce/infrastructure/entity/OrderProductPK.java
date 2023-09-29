package com.icodeap.ecommerce.infrastructure.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderProductPK implements Serializable {


    @ManyToOne (fetch = FetchType.LAZY)
    private OrderEntity orderEntity;
    @ManyToOne (fetch = FetchType.LAZY)
    private ProductEntity productEntity;
}
