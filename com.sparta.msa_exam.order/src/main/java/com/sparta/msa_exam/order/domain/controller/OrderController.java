package com.sparta.msa_exam.order.domain.controller;

import com.sparta.msa_exam.order.domain.dto.req.ReqOrderPostDTO;
import com.sparta.msa_exam.order.domain.dto.res.ResDTO;
import com.sparta.msa_exam.order.domain.dto.res.ResOrderGetByIdDTO;
import com.sparta.msa_exam.order.domain.dto.res.ResOrderPostDTO;
import com.sparta.msa_exam.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<ResDTO<ResOrderPostDTO>> createOrder(@RequestHeader("X-User-Id") Long userId,
                                                               @RequestParam String username,
                                                               @RequestBody ReqOrderPostDTO dto) {

        return orderService.createOrder(userId, username, dto);
    }

    // 주문 단건 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<ResDTO<ResOrderGetByIdDTO>> getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

}
