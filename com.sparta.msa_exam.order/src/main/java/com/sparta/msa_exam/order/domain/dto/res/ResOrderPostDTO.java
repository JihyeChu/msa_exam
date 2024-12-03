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

    private Order order;

    public static ResOrderPostDTO of(OrderEntity orderEntity) {
        return ResOrderPostDTO.builder()
                .order(Order.from(orderEntity, orderEntity.getOrderProducts()))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Order {

        private String createdBy;
        private String status;
        private List<OrderProduct> orderProducts;

        private static Order from(OrderEntity orderEntity, List<OrderProductEntity> orderLineEntities) {
            return Order.builder()
                    .createdBy(orderEntity.getCreatedBy())
                    .status(orderEntity.getStatus().getStatus())
                    .orderProducts(OrderProduct.from(orderLineEntities))
                    .build();
        }


        @Getter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        private static class OrderProduct {

            private Long productId;
            private int count;
            private int supplyPrice;

            private static List<OrderProduct> from(List<OrderProductEntity> orderProductEntities) {
                return orderProductEntities.stream()
                        .map(OrderProduct::from)
                        .toList();
            }

            private static OrderProduct from(OrderProductEntity orderLineEntity) {
                return OrderProduct.builder()
                        .productId(orderLineEntity.getProductId())
                        .count(orderLineEntity.getCount())
                        .supplyPrice(orderLineEntity.getSupplyPrice())
                        .build();
            }
        }

    }

}
