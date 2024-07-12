package com.candeus.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.Admin;
import com.candeus.product.domain.req.LoginForm;
import com.candeus.product.domain.vo.AdminVo;
import com.candeus.product.mapper.AdminMapper;
import com.candeus.product.service.AdminService;
import com.candeus.product.tool.AdminHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.candeus.product.common.constant.RedisConstants.LOGIN_ADMIN_KEY;
import static com.candeus.product.common.constant.RedisConstants.LOGIN_USER_TTL;

@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(LoginForm loginForm) {
        String userId = loginForm.getUserId();
        //1.校验用户编号
        if (userId == null || userId == "") {
            //2.如果不符合
            return Result.fail("账号格式错误");
        }
        //3.一致，根据用户编号查询用户
        Admin admin = query().eq("admin_id", userId).one();
        //4.判断用户是否存在
        if (admin == null){
            //5.用户不存在，直接返回
            return Result.fail("该用户不存在，请联系管理员");
        }
        //6.校验用户密码
        String password = loginForm.getPassword(); // TODO EncodeUtils.generatePassword(loginForm.getPassword());  后续需要进行加密解密
        if(!admin.getPassword().equals(password)){
            return Result.fail("密码错误，请重试");
        }
        //7.保存用户消息到（redis）
        // 7.1 生成token
        String token = UUID.randomUUID().toString(true);
        String tokenKey = LOGIN_ADMIN_KEY + token;
        // 7.2 将Admin对象转为Hash存储
        AdminVo adminVo = BeanUtil.copyProperties(admin, AdminVo.class);
        Map<String, Object> map = BeanUtil.beanToMap(adminVo,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue) -> fieldValue.toString()));
        // 7.3 存储
        stringRedisTemplate.opsForHash().putAll(tokenKey, map);
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return Result.ok(token);
    }

    @Override
    public Result logout(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("authorization");
        //获取redis中的用户
        if (StrUtil.isBlank(token)){
            return Result.fail("用户已过期");
        }
        // 从Redis中查询是否存在该登录的记录key，然后对其内容进行清空
        String key = LOGIN_ADMIN_KEY + token;
        if (stringRedisTemplate.hasKey(key)) {
            //清空redis中的值
            deleteAllFieldsInHash(key);
            //清除ThreadLocal存储的值
            AdminHolder.removeAdmin();
            return Result.ok("退出登录成功");
        }
        return Result.fail("用户已过期");
    }

    @Override
    public Result getMe() {
        AdminVo admin = AdminHolder.getAdmin();
        return Result.ok(admin);
    }

    public  void deleteAllFieldsInHash(String hashKey) {
        HashOperations<String, String, String> hashOps = stringRedisTemplate.opsForHash();
        Set<String> fields = hashOps.keys(hashKey);

        if (fields != null && !fields.isEmpty()) {
            List<String> fieldsList = new ArrayList<>(fields);
            hashOps.delete(hashKey, fieldsList.toArray(new String[0]));
        } else {
            // 处理字段为空的情况，例如记录日志或者执行其他操作
            log.info("哈希表中没有字段需要删除");
        }
    }
}
