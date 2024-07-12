package com.candeus.product.service;

import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.FieldDisplayConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.candeus.product.domain.req.FieldDisplayConfigForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
public interface FieldDisplayConfigService extends IService<FieldDisplayConfig> {

    Result getAllFieldDisplayConfig();

    Result updateFieldDisplayConfig(String id, FieldDisplayConfigForm configForm);

    List<FieldDisplayConfig> getDisplyConfigList();
}
