package com.candeus.product.mapper;

import com.candeus.product.domain.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    Product selectByProductSerial(String productSerial);

    Product findByTypeAndSerial(String productType, String productSerial);
}
