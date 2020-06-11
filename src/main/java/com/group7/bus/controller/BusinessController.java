package com.group7.bus.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/")
@RequiresRoles("ADMIN")
public class BusinessController {

    /** 跳转到挂号时间管理 */
    @RequestMapping("toDoctorTimeManager")
    public String toDoctorTimeManager() {
        return "/business/register/doctorTimeManager";
    }

    /** 跳转到挂号管理 */
    @RequestMapping("toRegisterManager")
    public String toRegisterManager() {
        return "/business/register/registerTimeManager";
    }

    /** 跳转到挂号缴费管理 */
    @RequestMapping("toPaymentManager")
    public String toPaymentManager() {
        return "/business/register/paymentManager";
    }

}
