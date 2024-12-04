package com.sparta.msa_exam.product.domain.dto.res;

import com.sparta.msa_exam.product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResProductForOrderDTO {

    private List<ProductDTO> productDTOList;

    public static ResProductForOrderDTO of(List<ProductEntity> productEntities) {
        return ResProductForOrderDTO.builder()
                .productDTOList(ProductDTO.from(productEntities))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductDTO {

        private Long productId;
        private String name;
        private int supplyPrice;

        private static List<ProductDTO> from(List<ProductEntity> productEntities) {
            return productEntities.stream()
                    .map(ProductDTO::from)
                    .toList();
        }

        private static ProductDTO from(ProductEntity productEntity) {
            return ProductDTO.builder()
                    .productId(productEntity.getId())
                    .name(productEntity.getName())
                    .supplyPrice(productEntity.getSupplyPrice())
                    .build();
        }
    }
}
