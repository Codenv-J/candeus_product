package com.candeus.product.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String brand;
    private String productType;
    private String productModel;
    private String productSerial;
    private String productionCycle;
    private String proxyFactory;
    private Boolean isDeleted;
    private String createdAt;
    private String updatedAt;
}
