package com.group7.sys.controller;

import com.group7.sys.common.ActiverUser;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Loginfo;
import com.group7.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group7.sys.exception.medMISException;

import java.util.Date;

// 登录控制器
@RestController
@RequestMapping("/api/login/")
public class LoginController {

  @Autowired private LoginfoService loginfoService;

  @RequestMapping("/")
  public ActiverUser login(String loginName, String password) throws medMISException {

    Subject subject = SecurityUtils.getSubject();

    AuthenticationToken token = new UsernamePasswordToken(loginName, password);
    try {
      subject.login(token);
      ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
      WebUtils.getSession().setAttribute("user", activerUser.getUser());

      // 记录登录日志
      Loginfo entity = new Loginfo();
      entity.setLoginName(activerUser.getUser().getName() + "-" + activerUser.getUser().getName());
      entity.setLoginIp(WebUtils.getRequest().getRemoteAddr());
      entity.setLoginTime(new Date());
      loginfoService.save(entity);

      return activerUser;
    } catch (AuthenticationException e) {
      e.printStackTrace();
      throw new medMISException("无效的账号或密码", HttpStatus.UNAUTHORIZED);
    }
  }
}
