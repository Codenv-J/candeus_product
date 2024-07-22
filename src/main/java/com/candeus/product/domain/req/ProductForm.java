package com.candeus.product.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private String brand;
    private String productType;
    private String productModel;
    private String productSerial;
    private String productionCycle;
    private String proxyFactory;
}
