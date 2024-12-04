package com.sparta.msa_exam.order.domain.service;

import com.sparta.msa_exam.order.domain.client.ProductClient;
import com.sparta.msa_exam.order.domain.dto.req.ReqOrderPostDTO;
import com.sparta.msa_exam.order.domain.dto.res.*;
import com.sparta.msa_exam.order.model.entity.OrderEntity;
import com.sparta.msa_exam.order.model.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Transactional
    @CircuitBreaker(name = "orderService", fallbackMethod = "handlerOrderPostFailure")
    public ResponseEntity<ResDTO<ResOrderPostDTO>> createOrder(Long userId, String username, ReqOrderPostDTO dto) {

        List<Long> productIds = getIds(dto);

        ResProductGetDTO clientBy = productClient.getProduct(productIds);

        Map<Long, Integer> supplyPriceMap = getMap(clientBy);

        OrderEntity orderEntity = orderRepository.save(dto.toEntity(userId, username, supplyPriceMap));

        return new ResponseEntity<>(
                ResDTO.<ResOrderPostDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("주문 생성에 성공하였습니다.")
                        .data(ResOrderPostDTO.of(orderEntity))
                        .build(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<ResDTO<Object>> handlerOrderPostFailure(Long userId, String username, ReqOrderPostDTO dto, Throwable t) {
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .message("잠시 후에 주문 추가를 요청 해주세요.")
                        .data(
                                ResOrderFailureDTO.of(
                                        t.getLocalizedMessage(), userId, username, dto
                                )
                        )
                        .build(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResDTO<ResOrderGetByIdDTO>> getOrder(Long orderId) {

        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("존재하지 않는 주문입니다.")
        );

        return new ResponseEntity<>(
                ResDTO.<ResOrderGetByIdDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("주문 단건 조회에 성공했습니다.")
                        .data(ResOrderGetByIdDTO.of(orderEntity))
                        .build(),
                HttpStatus.OK
        );

    }

    private static Map<Long, Integer> getMap(ResProductGetDTO clientBy) {
        return clientBy.getProductDTOList()
                .stream()
                .collect(Collectors.toMap(
                        ResProductGetDTO.ProductDTO::getProductId,
                        ResProductGetDTO.ProductDTO::getSupplyPrice
                ));
    }

    private static List<Long> getIds(ReqOrderPostDTO dto) {
        return dto.getProductDTOList().stream()
                .map(ReqOrderPostDTO.ProductDTO::getProductId)
                .toList();
    }
}
