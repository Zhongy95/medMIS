package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.group7.sys.common.DataGridView;
import com.group7.sys.common.TreeNode;
import com.group7.sys.common.TreeNodeBuilder;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Permission;
import com.group7.sys.entity.User;
import com.group7.sys.service.PermissionService;
import com.group7.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.group7.sys.common.Constast.*;

/** 菜单管理 */
@RestController
@RequestMapping("api/menu/")
public class MenuController {

  @Autowired private PermissionService permissionService;

  @RequestMapping("/loadIndexLeftMenuJson")
  public DataGridView loadIndexLeftMenuJson(PermissionVo permissionvo) {
    // 查询所有菜单
    QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
    // 设置查询菜单条件
    queryWrapper.eq("type", TYPE_MENU);
    queryWrapper.eq("available", AVAILABLE_TRUE);
    // 获取当前登录账号信息
    User user = (User) WebUtils.getSession().getAttribute("user");
    System.out.println(user.toString());
    List<Permission> list = null;
    if (user.getAvailable().equals(USER_TYPE_SUPER)) {
      list = permissionService.list(queryWrapper);
    } else {
      // 根据用户ID+角色+权限去查询

      list = permissionService.list(queryWrapper);
    }

    List<TreeNode> treeNodes = new ArrayList<>();

    //

    for (Permission permission : list) {
      Integer id = permission.getId();
      Integer pid = permission.getPid();
      String title = permission.getTitle();
      String icon = permission.getIcon();
      String href = permission.getHref();
      Boolean spread = permission.getOpen() == OPEN_TRUE ? true : false;
      treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
    }
    // 构造层级关系
    List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);

    return new DataGridView(list2);
  }
}
