package com.sparta.msa_exam.product.domain.controller;

import com.sparta.msa_exam.product.domain.dto.req.ReqProductPostDTO;
import com.sparta.msa_exam.product.domain.dto.res.ResDTO;
import com.sparta.msa_exam.product.domain.dto.res.ResProductGetDTO;
import com.sparta.msa_exam.product.domain.dto.res.ResProductPostDTO;
import com.sparta.msa_exam.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 상품 생성
    @PostMapping
    public ResponseEntity<ResDTO<ResProductPostDTO>> createProduct(@RequestHeader("X-User-Name") String username,
                                                                   @RequestBody ReqProductPostDTO reqDto) {

        return productService.createProduct(username, reqDto);
    }




}
