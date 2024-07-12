package com.candeus.product.tool;


import com.candeus.product.domain.vo.AdminVo;

public class AdminHolder {
    private static final ThreadLocal<AdminVo> tl = new ThreadLocal<>();

    public static void saveAdmin(AdminVo admin){
        tl.set(admin);
    }

    public static AdminVo getAdmin(){
        return tl.get();
    }

    public static void removeAdmin(){
        tl.remove();
    }
}
