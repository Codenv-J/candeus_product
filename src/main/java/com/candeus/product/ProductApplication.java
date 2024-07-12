package com.candeus.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan("com.candeus.product.mapper")
public class ProductApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ProductApplication.class, args);
    }
}
