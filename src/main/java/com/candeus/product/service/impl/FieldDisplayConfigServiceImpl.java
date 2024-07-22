package com.candeus.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.FieldDisplayConfig;
import com.candeus.product.domain.req.FieldDisplayConfigForm;
import com.candeus.product.domain.vo.AdminVo;
import com.candeus.product.mapper.FieldDisplayConfigMapper;
import com.candeus.product.service.FieldDisplayConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.candeus.product.tool.AdminHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.candeus.product.common.constant.AdminLevelConstant.SUPER_ADMIN;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Codenj
 * @since 2024-07-12
 */
@Service
public class FieldDisplayConfigServiceImpl extends ServiceImpl<FieldDisplayConfigMapper, FieldDisplayConfig> implements FieldDisplayConfigService {

    @Resource
    private FieldDisplayConfigMapper fieldDisplayConfigMapper;

    @Override
    public Result getAllFieldDisplayConfig() {
        QueryWrapper<FieldDisplayConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<FieldDisplayConfig> configList = fieldDisplayConfigMapper.selectList(queryWrapper);
        return Result.ok(configList);
    }

    @Override
    public Result updateFieldDisplayConfig(String id, FieldDisplayConfigForm configForm) {
        if (id == null){
            return Result.fail("id参数错误");
        }
        AdminVo admin = AdminHolder.getAdmin();
        Integer adminLevel = admin.getAdminLevel();
        if (adminLevel != SUPER_ADMIN.getLevel()) {
            return Result.build(40010,"权限不足,请联系超级管理员!",null);
        }
        FieldDisplayConfig displayConfigInDB = fieldDisplayConfigMapper.selectById(id);
        if (displayConfigInDB != null){
            if (configForm.getIsDisplay() != null){
                displayConfigInDB.setIsDisplay(configForm.getIsDisplay());
            }else{
                return Result.fail("是否展示参数错误");
            }
            fieldDisplayConfigMapper.updateById(displayConfigInDB);
            return Result.ok();
        }
        return Result.fail("更新失败,请重新操作！");
    }

    @Override
    public List<FieldDisplayConfig> getDisplyConfigList() {
        QueryWrapper<FieldDisplayConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_display",true);
        queryWrapper.orderByAsc("id");
        return fieldDisplayConfigMapper.selectList(queryWrapper);
    }
}
