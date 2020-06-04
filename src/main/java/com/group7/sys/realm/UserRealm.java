package com.group7.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group7.sys.common.ActiverUser;

import com.group7.sys.entity.User;
import com.group7.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
  @Autowired private UserService userService;

  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }

  /** 认证方法 */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("loginname", authenticationToken.getPrincipal().toString());
    // 通过用户名从数据库中查询出该用户
    User user = userService.getOne(queryWrapper);

    if (null != user) {
      ActiverUser activerUser = new ActiverUser();
      activerUser.setUser(user);
      //            ByteSource credentialsSalt =  ByteSource.Util.bytes(user.getSalt());
      /** 参数说明： 参数1：活动的User 参数2：从数据库里面查询出来的密码(已经通过MD5加密) 参数3：从数据库里面查询出来的盐 参数4：当前类名 */
      SimpleAuthenticationInfo info =
          new SimpleAuthenticationInfo(activerUser, user.getPassword(), this.getName());
      return info;
    }

    return null;
  }
  /** 授权 */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    return null;
  }
}
