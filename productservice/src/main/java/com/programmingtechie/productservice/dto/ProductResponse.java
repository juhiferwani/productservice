package com.programmingtechie.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    /*
    Exact same as Product.java model Entity
    but Good Practise is not to expose model to outside world
    as future addition of Product class fields might get exposed
    that are necessary for business logic
     */
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
