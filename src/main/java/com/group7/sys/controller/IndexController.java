package com.group7.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

        //跳转到登陆页面
        @RequestMapping("login")
        public String login() {
            return "index/login";
        }

        @RequestMapping("signup")
        public String signup() {
            return "index/signup";
        }

        //登陆成功，跳转到首页
        @RequestMapping("home")
        public String index() {
            return "index/index";
        }
        /**
         * 跳转到工作台
         */
        @RequestMapping("toDeskManager")
        public String toDeskManager(){
            return "index/deskManager";
        }

        /**
         * 跳转到工作台
         */
        @RequestMapping("toUserInfo")
        public String toUserInfo(){
            return "index/userInfo";
        }

    }
