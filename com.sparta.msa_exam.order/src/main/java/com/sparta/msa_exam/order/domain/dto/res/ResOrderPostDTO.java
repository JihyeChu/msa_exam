package com.sparta.msa_exam.order.domain.dto.res;

import com.sparta.msa_exam.order.model.entity.OrderEntity;
import com.sparta.msa_exam.order.model.entity.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResOrderPostDTO {

    private OrderDTO orderDTO;

    public static ResOrderPostDTO of(OrderEntity orderEntity) {
        return ResOrderPostDTO.builder()
                .orderDTO(OrderDTO.from(orderEntity, orderEntity.getOrderProducts()))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class OrderDTO {

        private String createdBy;
        private String status;
        private List<OrderProductDTO> orderProductDTOList;

        private static OrderDTO from(OrderEntity orderEntity, List<OrderProductEntity> orderLineEntities) {
            return OrderDTO.builder()
                    .createdBy(orderEntity.getCreatedBy())
                    .status(orderEntity.getOrderStatus().getStatus())
                    .orderProductDTOList(OrderProductDTO.from(orderLineEntities))
                    .build();
        }


        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        private static class OrderProductDTO {

            private Long productId;
            private int count;
            private int supplyPrice;

            private static List<OrderProductDTO> from(List<OrderProductEntity> orderProductEntities) {
                return orderProductEntities.stream()
                        .map(OrderProductDTO::from)
                        .toList();
            }

            private static OrderProductDTO from(OrderProductEntity orderProductEntity) {
                return OrderProductDTO.builder()
                        .productId(orderProductEntity.getProductId())
                        .count(orderProductEntity.getCount())
                        .supplyPrice(orderProductEntity.getSupplyPrice())
                        .build();
            }
        }

    }

}
