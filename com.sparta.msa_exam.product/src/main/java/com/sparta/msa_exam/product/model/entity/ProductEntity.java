package com.sparta.msa_exam.product.model.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class ProductEntity {

    @Id @Tsid
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;

    @Column(name = "quantity")
    private int quantity;

    @Builder
    public ProductEntity(String name, int supplyPrice, int quantity) {
        this.name = name;
        this.supplyPrice = supplyPrice;
        this.quantity = quantity;
    }

}
