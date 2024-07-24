package com.candeus.product.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {
    private String adminId;
    private String name;
    private String password;
    private Integer adminLevel;
}
