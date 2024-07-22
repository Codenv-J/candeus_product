package com.candeus.product.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class ProductExcelData {
    @ExcelProperty("品牌")
    @ColumnWidth(10)
    private String brand;

    @ExcelProperty("产品类型")
    @ColumnWidth(10)
    private String productType;

    @ExcelProperty("产品型号")
    @ColumnWidth(10)
    private String productModel;

    @ExcelProperty("产品序列号")
    @ColumnWidth(10)
    private String productSerial;

    @ExcelProperty("出厂周期")
    @ColumnWidth(10)
    private String productionCycle;

    @ExcelProperty("代理商")
    @ColumnWidth(10)
    private String proxyFactory;
}