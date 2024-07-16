package com.candeus.product.service;

import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.candeus.product.domain.req.ProductForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
public interface ProductService extends IService<Product> {

    Result add(ProductForm productForm);

    Result importProducts(MultipartFile file);

    Result updateProduct(String id, ProductForm productForm);

    Result deleteProduct(String id);

    Result getProduct(String id);

    Result getProductList(String pageNum, String pageSize);

    Result getSearchProductList(String pageNum, String pageSize, String productSerial);

    Result findByTypeAndSerial(String productType, String productSerial);
}
