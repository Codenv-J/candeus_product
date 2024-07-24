package com.candeus.product.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateFrom {
    private String name;
    private String password;
    private Integer adminLevel;
}
