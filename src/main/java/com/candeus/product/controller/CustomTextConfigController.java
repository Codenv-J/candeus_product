package com.candeus.product.controller;


import com.candeus.product.common.result.Result;
import com.candeus.product.domain.req.CustomTextConfigForm;
import com.candeus.product.service.CustomTextConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
public class CustomTextConfigController {

    @Resource
    private CustomTextConfigService customTextConfigService;

    @GetMapping("/admin/customTextConfig/all")
    Result getAllCustomTextConfig() {
        return customTextConfigService.getAllCustomTextConfig();
    }

    @PostMapping("/admin/customTextConfig/update/{id}")
    Result updateCustomTextConfig(@PathVariable String id, @RequestBody CustomTextConfigForm configForm) {
        return customTextConfigService.updateCustomTextConfig(id,configForm);
    }

}
