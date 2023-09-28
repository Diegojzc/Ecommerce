package com.icodeap.ecommerce.infrastructure.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderProductPK implements Serializable {

    private static final long serialVersionUID = -1597542359330067501L;
    @ManyToOne (fetch = FetchType.LAZY)
    private OrderEntity orderEntity;
    @ManyToOne (fetch = FetchType.LAZY)
    private ProductEntity productEntity;
}
