package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.group7.sys.common.DataGridView;
import com.group7.sys.common.TreeNode;
import com.group7.sys.common.TreeNodeBuilder;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Permission;
import com.group7.sys.entity.RolePermission;
import com.group7.sys.entity.User;
import com.group7.sys.service.PermissionService;
import com.group7.sys.service.RolePermissionService;
import com.group7.sys.service.impl.PermissionServiceImpl;
import com.group7.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.group7.sys.common.Constast.*;

/** 菜单管理 */
@RestController
@RequestMapping("/api/menu/")
public class MenuController {

  private final PermissionServiceImpl permissionServiceImpl;

  @Autowired
  public MenuController(PermissionServiceImpl permissionServiceImpl) {
    this.permissionServiceImpl = permissionServiceImpl;
  }

  @RequestMapping("/loadIndexLeftMenuJson")
  public DataGridView loadIndexLeftMenuJson(PermissionVo permissionvo) {

    // 获取当前登录账号信息
    User user = (User) WebUtils.getSession().getAttribute("user");
    System.out.println(user.toString());

    // 查询所有菜单
    Integer role_id = user.getRoleId();
    List<Permission> list = null;
    list = permissionServiceImpl.getMenu(role_id);

    // 生成菜单栏项目
    List<TreeNode> treeNodes = new ArrayList<>();
    for (Permission permission : list) {
      Integer id = permission.getPermissionId();
      Integer pid = permission.getPid();
      String title = permission.getTitle();
      String icon = permission.getIcon();
      String href = permission.getHref();
      Boolean spread = permission.getOpen();
      treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
    }
    // 构造层级关系
    List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);

    return new DataGridView(list2);
  }
}
