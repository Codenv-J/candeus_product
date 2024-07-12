package com.candeus.product.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVo {
    private String adminId;
    private String avatar;
    private String emergencyContact;
    private Integer adminLevel;
    private String createTime;
    private String updateTime;
    private Boolean isDeleted;
}
