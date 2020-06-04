package com.group7.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/")
public class SystemController {


    //跳转到登陆页面
    @RequestMapping("login")
    public String login() {
        return "system/index/login";
    }

    @RequestMapping("signup")
    public String signup() {
        return "system/index/signup";
    }

    //登陆成功，跳转到首页
    @RequestMapping("home")
    public String index() {
        return "system/index/index";
    }
    /**
     * 跳转到工作台
     */
    @RequestMapping("deskManager")
    public String deskManager(){
        return "system/index/deskManager";
    }

    /**
     * 跳转到日志管理
     */

    @RequestMapping("loginfoManager")
    public String loginfoManager(){
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转到公告管理
     */

    @RequestMapping("noticeManager")
    public String noticeManager(){
        return "system/notice/noticeManager";
    }

    /**
     * 跳转到部门管理
     */

    @RequestMapping("deptManager")
    public String deptManager(){
        return "system/dept/deptManager";
    }

    /**
     * 跳转到部门管理-左
     */

    @RequestMapping("deptLeft")
    public String deptLeft(){
        return "system/dept/deptLeft";
    }
    /**
     * 跳转到部门管理-右
     */

    @RequestMapping("deptRight")
    public String deptRight(){
        return "system/dept/deptRight";
    }

}
