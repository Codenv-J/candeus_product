package com.candeus.product.controller;

import com.candeus.product.common.result.Result;
import com.candeus.product.domain.req.AccountForm;
import com.candeus.product.domain.req.AccountUpdateFrom;
import com.candeus.product.domain.req.LoginForm;
import com.candeus.product.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/admin/account/add")
    public Result addAccount(@RequestBody AccountForm form){
        return adminService.addAccount(form);
    }

    @PostMapping("/admin/account/update/{id}")
    public Result updateAccount(@PathVariable("id") String adminId, @RequestBody AccountUpdateFrom form){
        return adminService.updateAccount(adminId,form);
    }

    @GetMapping("/admin/account/all")
    public Result getAllAccount(@RequestParam(value = "pageSize",defaultValue = "10") String pageSize, @RequestParam(value = "pageNum",defaultValue = "1") String pageNum){
        return adminService.getAllAccountPage(pageSize,pageNum);
    }

    @DeleteMapping("/admin/account/remove/{id}")
    public Result removeAccount(@PathVariable("id") String adminId){
        return adminService.deleteAccount(adminId);
    }
}
