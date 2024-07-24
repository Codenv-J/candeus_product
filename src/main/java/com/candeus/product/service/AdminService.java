package com.candeus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.candeus.product.common.result.Result;
import com.candeus.product.domain.pojo.Admin;
import com.candeus.product.domain.req.AccountForm;
import com.candeus.product.domain.req.AccountUpdateFrom;
import com.candeus.product.domain.req.LoginForm;

import javax.servlet.http.HttpServletRequest;

public interface AdminService extends IService<Admin> {
    Result login(LoginForm loginForm);

    Result logout(HttpServletRequest request);

    Result getMe();

    Result addAccount(AccountForm form);

    Result deleteAccount(String adminId);

    Result getAllAccountPage(String pageSize, String pageNum);

    Result updateAccount(String adminId, AccountUpdateFrom form);
}
