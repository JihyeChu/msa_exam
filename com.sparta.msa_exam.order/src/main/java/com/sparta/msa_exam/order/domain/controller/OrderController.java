package com.sparta.msa_exam.order.domain.controller;

import com.sparta.msa_exam.order.domain.dto.req.ReqOrderPostDTO;
import com.sparta.msa_exam.order.domain.dto.res.ResDTO;
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

    @PostMapping
    public ResponseEntity<ResDTO<ResOrderPostDTO>> createOrder(@RequestHeader("X-User-Id") Long userId,
                                                               @RequestParam String username,
                                                               @RequestBody ReqOrderPostDTO reqDto) {

        return orderService.createOrder(userId, username, reqDto);
    }

}
