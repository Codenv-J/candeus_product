package com.candeus.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.CustomTextConfig;
import com.candeus.product.domain.req.CustomTextConfigForm;
import com.candeus.product.mapper.CustomTextConfigMapper;
import com.candeus.product.service.CustomTextConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
@Service
public class CustomTextConfigServiceImpl extends ServiceImpl<CustomTextConfigMapper, CustomTextConfig> implements CustomTextConfigService {

    @Resource
    private CustomTextConfigMapper customTextConfigMapper;

    @Override
    public Result getAllCustomTextConfig() {
        QueryWrapper<CustomTextConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<CustomTextConfig> textConfigList = customTextConfigMapper.selectList(queryWrapper);
        if (textConfigList != null) {
            return Result.ok(textConfigList);
        }else{
            return null;
        }

    }

    @Override
    @Transactional
    public Result updateCustomTextConfig(String id, CustomTextConfigForm configForm) {
        CustomTextConfig customTextConfig = customTextConfigMapper.selectById(id);
        if (customTextConfig != null) {
            customTextConfig.setCustomText(configForm.getCustomText());
            customTextConfig.setFieldName(configForm.getFieldName());
            customTextConfigMapper.updateById(customTextConfig);
            return Result.ok();
        }else{
            return Result.fail("更新失败");
        }
    }

    @Override
    public List<CustomTextConfig> getCustomTextConfigList() {
        QueryWrapper<CustomTextConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        return customTextConfigMapper.selectList(queryWrapper);
    }
}
