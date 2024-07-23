package com.candeus.product.controller;


import com.candeus.product.common.result.Result;
import com.candeus.product.domain.req.ProductForm;
import com.candeus.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
@Slf4j
@RestController
public class ProductController {

    @Resource
    private ProductService productService;


    @PutMapping("/admin/product/add")
    Result addProduct(@RequestBody ProductForm productForm) {
        return productService.add(productForm);
    }

    @PostMapping("/admin/product/getByidTypeNum")
    Result getProductByTypeAndSerial(String productType,String productSerial){
        return productService.findByTypeAndSerial(productType,productSerial);
    }

    @PostMapping("/admin/product/import")
    Result importProduct(MultipartFile file) {
        return productService.importProducts(file);
    }

    @PostMapping("/admin/product/update/{id}")
    Result updateProduct(@PathVariable String id, @RequestBody ProductForm productForm) {
        return productService.updateProduct(id,productForm);
    }

    @DeleteMapping("/admin/product/remove/{id}")
    Result deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/admin/product/get/{id}")
    Result getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/admin/product/all")
    Result getProductList(@RequestParam(value = "pageNum",defaultValue = "1") String pageNum, @RequestParam(value = "pageSize",defaultValue = "50") String pageSize,@RequestParam(value = "productSerial",required = false) String productSerial) {
        return productService.getProductList(pageNum,pageSize,productSerial);
    }

    @GetMapping("/user/product/all")
    Result getSearchProductList(@RequestParam(value = "pageNum",defaultValue = "1") String pageNum, @RequestParam(value = "pageSize",defaultValue = "10") String pageSize,@RequestParam(value = "productSerial",required = true) String productSerial) {
        return productService.getSearchProductList(pageNum,pageSize,productSerial);
    }
}
