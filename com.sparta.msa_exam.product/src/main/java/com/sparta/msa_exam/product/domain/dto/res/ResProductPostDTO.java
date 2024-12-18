package com.sparta.msa_exam.product.domain.dto.res;

import com.sparta.msa_exam.product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResProductPostDTO {

    private Product product;

    public static ResProductPostDTO of(ProductEntity productEntity) {
        return ResProductPostDTO.builder()
                .product(Product.from(productEntity))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        private Long id;
        private String name;
        private int supplyPrice;
        private int quantity;
        private String createBy;

        private static Product from(ProductEntity productEntity) {
            return Product.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .supplyPrice(productEntity.getSupplyPrice())
                    .quantity(productEntity.getQuantity())
                    .createBy(productEntity.getCreatedBy())
                    .build();
        }



    }
}
