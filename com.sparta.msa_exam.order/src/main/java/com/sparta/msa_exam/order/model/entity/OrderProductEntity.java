package com.sparta.msa_exam.order.model.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_products")
public class OrderProductEntity {

    @Id @Tsid
    @Column(name = "order_proudct_id", nullable = false)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;

    @Builder
    public OrderProductEntity(Long productId, int count, int supplyPrice) {
        this.productId = productId;
        this.count = count;
        this.supplyPrice = supplyPrice;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public void updateOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
