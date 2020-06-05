package com.group7.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/")
public class SystemController {

    /**
     * 跳转到日志管理
     */

    @RequestMapping("toLoginfoManager")
    public String toLoginfoManager(){
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转到公告管理
     */

    @RequestMapping("toNoticeManager")
    public String toNoticeManager(){
        return "system/notice/noticeManager";
    }

    /**
     * 跳转到部门管理
     */

    @RequestMapping("toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager";
    }

    /**
     * 跳转到部门管理-左
     */

    @RequestMapping("toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/deptLeft";
    }
    /**
     * 跳转到部门管理-右
     */

    @RequestMapping("toDeptRight")
    public String toDeptRight(){
        return "system/dept/deptRight";
    }

}
