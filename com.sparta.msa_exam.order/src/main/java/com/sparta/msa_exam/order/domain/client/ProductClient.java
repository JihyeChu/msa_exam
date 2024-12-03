package com.sparta.msa_exam.order.domain.client;

import com.sparta.msa_exam.order.domain.dto.res.ResProductGetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/products/details")
    ResProductGetDTO getProduct(@RequestBody List<Long> productIds);

}
