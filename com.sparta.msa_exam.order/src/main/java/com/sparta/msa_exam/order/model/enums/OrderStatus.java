package com.sparta.msa_exam.order.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    PENDING(Status.PENDING),        // 대기
    PROCESSING(Status.PROCESSING),  // 처리 : 배송중
    COMPLETED(Status.COMPLETED),    // 완료 : 배송완료
    CANCELLED(Status.CANCELLED),    // 취소
    REFUNDED(Status.REFUNDED);      // 환불

    private final String status;

    public static class Status {
        public static final String PENDING = "ORDER_PENDING";
        public static final String PROCESSING = "ORDER_PROCESSING";
        public static final String COMPLETED = "ORDER_COMPLETED";
        public static final String CANCELLED = "ORDER_CANCELLED";
        public static final String REFUNDED = "ORDER_REFUNDED";
    }

}
