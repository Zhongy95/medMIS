package com.group7.sys.controller;


import com.group7.sys.common.ActiverUser;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Loginfo;
import com.group7.sys.entity.User;
import com.group7.sys.service.UserService;
import com.group7.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.group7.sys.common.Constast.ROLE_PATIENT;

//注册控制器
@RestController
@RequestMapping("/api/signup/")
public class SignController {

    @Autowired private UserService userService;


    @RequestMapping("/")
    public ResultObj signup(UserVo uservo){


        uservo.setRoleId(ROLE_PATIENT);//设置

        String pwd = uservo.getPassword();
        String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        uservo.setPassword(md5Pwd);
        System.out.println("id_num"+uservo.getIdNum());

        try{
            this.userService.save(uservo);
            return ResultObj.ADD_SUCCES;

        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

}
