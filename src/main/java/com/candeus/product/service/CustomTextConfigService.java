package com.candeus.product.service;

import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.CustomTextConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.candeus.product.domain.req.CustomTextConfigForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
public interface CustomTextConfigService extends IService<CustomTextConfig> {

    Result getAllCustomTextConfig();

    Result updateCustomTextConfig(String id, CustomTextConfigForm configForm);

    List<CustomTextConfig> getCustomTextConfigList();
}
