package com.sparta.msa_exam.order.domain.dto.req;

import com.sparta.msa_exam.order.model.entity.OrderEntity;
import com.sparta.msa_exam.order.model.entity.OrderProductEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ReqOrderPostDTO {

    @Valid
    @NotNull(message = "상품 정보를 입력해주세요.")
    private List<ProductDTO> productDTOList;

    public OrderEntity toEntity(Long userId, String username, Map<Long, Integer> supplyPriceMap) {
        return OrderEntity.builder()
                .userId(userId)
                .username(username)
                .orderProducts(
                        productDTOList.stream().map(productDTO -> productDTO.from(supplyPriceMap.get(productDTO.productId))).toList()
                )
                .build();
    }

    @Getter
    @NoArgsConstructor
    public static class ProductDTO {

        @NotNull(message = "상품 ID를 입력해주세요.")
        private Long productId;

        @NotBlank(message = "주문 수량을 입력해주세요.")
        private int count;

        private OrderProductEntity from(int supplyPrice){
            return OrderProductEntity.builder()
                    .productId(productId)
                    .count(count)
                    .supplyPrice(supplyPrice * count)
                    .build();
        }
    }


}
