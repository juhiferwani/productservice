package com.programmingtechie.productservice.service;

import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    /*
    Lombok Issue in intellij Solution
    https://stackoverflow.com/questions/63151172/how-to-fix-lombok-builder-method-does-not-seem-to-be-recognised-in-intellij
    Mongo Db on Windows:
    https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/?_ga=2.216811934.924151182.1720451308-75737662.1720451307&_gac=1.20951754.1720451472.Cj0KCQjw-ai0BhDPARIsAB6hmP42taK_maPte2ZNrAHbc-aJuCmpfmqVtGAQ0ab077KEUACl1KBKI_waAsJjEALw_wcB#std-label-run-mongodb-from-cmd
     */
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
