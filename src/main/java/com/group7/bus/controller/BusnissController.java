package com.group7.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BusnissController {


    //跳转到登陆页面
    @RequestMapping("login")
    public String login() {
        return "business/index/login";
    }

    @RequestMapping("signup")
    public String signup() {
        return "business/index/signup";
    }

    //登陆成功，跳转到首页
    @RequestMapping("home")
    public String index() {
        return "business/index/index";
    }
    /**
     * 跳转到工作台
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "business/index/deskManager";
    }
}
