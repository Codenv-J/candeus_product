package com.candeus.product.controller;


import com.candeus.product.common.result.Result;
import com.candeus.product.domain.req.FieldDisplayConfigForm;
import com.candeus.product.service.FieldDisplayConfigService;
import com.candeus.product.service.impl.FieldDisplayConfigServiceImpl;
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
public class FieldDisplayConfigController {
    @Resource
    private FieldDisplayConfigService fieldDisplayConfigService;

    @GetMapping("/admin/fieldDisplayConfig/all")
    Result getAllFieldDisplayConfig() {
        return fieldDisplayConfigService.getAllFieldDisplayConfig();
    }

    @PostMapping("/admin/fieldDisplayConfig/update/{id}")
    Result updateFieldDisplayConfig(@PathVariable String id, @RequestBody FieldDisplayConfigForm configForm) {
        return fieldDisplayConfigService.updateFieldDisplayConfig(id,configForm);
    }

}
