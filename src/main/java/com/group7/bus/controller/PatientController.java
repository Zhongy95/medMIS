package com.group7.bus.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/patient")
@RequiresRoles("PATIENT")
public class PatientController {

    /** 跳转到挂号时间 */
    @RequestMapping("toDoctorTimeManager")
    public String toDoctorTimeManager() {
        return "business/register/doctorTimeManager";
    }

    /** 跳转到挂号时间-左 */
    @RequestMapping("toDoctorTimeLeft")
    public String toDeptLeft() {
        return "business/register/doctorTimeLeft";
    }
    /** 跳转到挂号时间-右 */
    @RequestMapping("toDoctorTimeRight")
    public String toDeptRight() {
        return "business/register/doctorTimeRight";
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
