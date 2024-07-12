package com.candeus.product.domain.req;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDisplayConfigForm {
    private String fieldName;
    private Boolean isDisplay;
}
