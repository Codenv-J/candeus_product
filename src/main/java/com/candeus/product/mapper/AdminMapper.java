package com.candeus.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.candeus.product.domain.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
