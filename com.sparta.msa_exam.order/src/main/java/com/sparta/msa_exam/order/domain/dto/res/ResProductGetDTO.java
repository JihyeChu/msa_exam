package com.sparta.msa_exam.order.domain.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResProductGetDTO {

    private List<ProductDTO> productDTOList;

    @Getter
    @NoArgsConstructor
    public static class ProductDTO {
        private Long productId;
        private String name;
        private int supplyPrice;
    }

}
