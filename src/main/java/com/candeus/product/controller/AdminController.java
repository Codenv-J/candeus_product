package com.candeus.product.controller;

import com.candeus.product.common.result.Result;
import com.candeus.product.domain.req.LoginForm;
import com.candeus.product.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/admin/login")
    public Result login(@RequestBody LoginForm loginForm){
        return adminService.login(loginForm);
    }

    @GetMapping("/admin/logout")
    public Result logout(HttpServletRequest request){
        return adminService.logout(request);
    }

    @GetMapping("/admin/me")
    public Result me(){
        return adminService.getMe();
    }
}
