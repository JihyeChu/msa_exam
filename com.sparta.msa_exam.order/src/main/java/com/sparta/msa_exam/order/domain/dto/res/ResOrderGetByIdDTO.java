package com.sparta.msa_exam.order.domain.dto.res;

import com.sparta.msa_exam.order.model.entity.OrderEntity;
import com.sparta.msa_exam.order.model.entity.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResOrderGetByIdDTO {

    private OrderDTO orderDTO;

    public static ResOrderGetByIdDTO of(OrderEntity orderEntity) {
        return ResOrderGetByIdDTO.builder()
                .orderDTO(OrderDTO.from(orderEntity))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDTO {

        private Long orderId;
        private String status;
        private LocalDateTime createdAt;
        private List<OrderProductDTO> orderProductDTOList;

        private static OrderDTO from(OrderEntity orderEntity) {
            return OrderDTO.builder()
                    .orderId(orderEntity.getId())
                    .status(orderEntity.getOrderStatus().getStatus())
                    .orderProductDTOList(OrderProductDTO.from(orderEntity.getOrderProducts()))
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
                return orderProductEntities.stream().map(OrderProductDTO::from).toList();
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
