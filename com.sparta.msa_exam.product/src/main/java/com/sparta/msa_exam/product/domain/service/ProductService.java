package com.sparta.msa_exam.product.domain.service;

import com.sparta.msa_exam.product.domain.dto.req.ReqProductPostDTO;
import com.sparta.msa_exam.product.domain.dto.res.ResDTO;
import com.sparta.msa_exam.product.domain.dto.res.ResProductGetDTO;
import com.sparta.msa_exam.product.domain.dto.res.ResProductPostDTO;
import com.sparta.msa_exam.product.model.entity.ProductEntity;
import com.sparta.msa_exam.product.model.repository.ProductRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ResponseEntity<ResDTO<ResProductPostDTO>> createProduct(String username, ReqProductPostDTO reqDto) {

        // 상품 이름 중복 검증
        productRepository.findByName(reqDto.getProduct().getName())
                .ifPresent(productEntity -> {
                    throw new DuplicateRequestException("이미 존재하는 상품입니다.");
                });

        // 상품 생성
        ProductEntity productEntity = productRepository.save(reqDto.getProduct().toEntity(username));

        return new ResponseEntity<>(
                ResDTO.<ResProductPostDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("상품 생성에 성공했습니다.")
                        .data(ResProductPostDTO.of(productEntity))
                        .build(),
                HttpStatus.CREATED
        );

    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResDTO<ResProductGetDTO>> getAllProduct(Pageable pageable) {

        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);

        return new ResponseEntity<>(
                ResDTO.<ResProductGetDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("상품 조회에 성공했습니다.")
                        .data(ResProductGetDTO.of(productEntityPage))
                        .build(),
                HttpStatus.OK
        );
    }

}

