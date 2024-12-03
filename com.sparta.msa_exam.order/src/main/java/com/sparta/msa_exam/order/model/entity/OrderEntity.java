package com.sparta.msa_exam.order.model.entity;

import com.sparta.msa_exam.order.model.enums.OrderStatus;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class OrderEntity {

    @Id @Tsid
    @Column(name = "order_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OrderProductEntity> orderProducts = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Builder
    public OrderEntity(Long userId, String username, List<OrderProductEntity> orderProducts) {
        this.userId = userId;
        this.createdBy = username;
        this.modifiedBy = username;
        this.status = OrderStatus.PROCESSING;
        addOrderProductEntities(orderProducts);
    }

    public void addOrderProductEntities(List<OrderProductEntity> orderProductEntities){
        this.orderProducts.addAll(orderProductEntities);
        orderProductEntities.forEach(orderProductEntity -> orderProductEntity.updateOrderEntity(this));
    }

}
