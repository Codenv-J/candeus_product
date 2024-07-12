package com.candeus.product.domain.req;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomTextConfigForm {
    private String fieldName;
    private String customText;
}
