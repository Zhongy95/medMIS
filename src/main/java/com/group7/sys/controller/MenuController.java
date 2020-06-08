package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.*;
import com.group7.sys.entity.Permission;
import com.group7.sys.entity.RolePermission;
import com.group7.sys.entity.User;
import com.group7.sys.service.DeptService;
import com.group7.sys.service.PermissionService;
import com.group7.sys.service.RolePermissionService;
import com.group7.sys.service.impl.PermissionServiceImpl;
import com.group7.sys.vo.PermissionVo;
import com.group7.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
      Boolean spread = permission.getOpened();
      treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
    }
    // 构造层级关系
    List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);

    return new DataGridView(list2);
  }

  /** 菜单管理开始* */
  @Autowired private PermissionService permissionService;

  /**
   * 菜单页面控制器
   *
   * @return
   */
  @RequestMapping("loadMenuManagerLeftTreeJson")
  public DataGridView loadMenuManagerLeftTreeJson() {
    QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("type", TYPE_MENU); // 控制只能查询菜单
    List<Permission> list = this.permissionService.list(queryWrapper);
    List<TreeNode> treeNodes = new ArrayList<>();
    for (Permission menu : list) {
      Boolean spread = menu.getOpened();
      treeNodes.add(new TreeNode(menu.getPermissionId(), menu.getPid(), menu.getTitle(), spread));
    }
    return new DataGridView(treeNodes);
  }

  @RequestMapping("loadAllMenu")
  public DataGridView loadAllMenu(PermissionVo permissionVo) {
    IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
    QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
    // 输入给定查询条件，默认无
    queryWrapper.eq("type", TYPE_MENU); // 控制只能查询菜单
    queryWrapper.like(
        StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
    queryWrapper.orderByDesc("order_num"); // 排序依据
    queryWrapper
        .eq(permissionVo.getPermissionId() != null, "permission_id", permissionVo.getPermissionId())
        .or()
        .eq(permissionVo.getPid() != null, "pid", permissionVo.getPid());

    this.permissionService.page(page, queryWrapper);

    return new DataGridView(page.getTotal(), page.getRecords());
  }
  /**
   * 添加菜单
   *
   * @param permissionVo
   * @return
   */
  @RequestMapping("addMenu")
  public ResultObj addMenu(PermissionVo permissionVo) {
    try {
      permissionVo.setType(TYPE_MENU); // 添加类型
      permissionService.save(permissionVo);
      return ResultObj.ADD_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.ADD_ERROR;
    }
  }

  /**
   * 加载排序码
   *
   * @return
   */
  @RequestMapping("loadMenuMaxOrderNum")
  public Map<String, Object> loadMenuMaxOrderNum() {
    Map<String, Object> map = new HashMap<String, Object>();
    QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("type", TYPE_MENU); // 控制只能查询菜单
    queryWrapper.orderByDesc("order_num");
    IPage<Permission> page = new Page<>(1, 1);
    List<Permission> list = permissionService.page(page, queryWrapper).getRecords();
    if (list.size() > 0) {
      map.put("value", list.get(0).getOrderNum() + 1);
    } else {
      map.put("value", 1);
    }
    return map;
  }

  /**
   * 更新菜单
   *
   * @param permissionVo
   * @return
   */
  @RequestMapping("updateMenu")
  public ResultObj updateMenu(PermissionVo permissionVo) {
    try {
      permissionService.updateById(permissionVo);

      return ResultObj.UPDATE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.UPDATE_ERROR;
    }
  }

  /**
   * 检查当前菜单是否有子菜单
   *
   * @param permissionVo
   * @return
   */
  @RequestMapping("checkMenuHasChildrenNode")
  public Map<String, Object> checkMenuHasChildrenNode(PermissionVo permissionVo) {
    Map<String, Object> map = new HashMap<String, Object>();
    QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("type", TYPE_MENU); // 控制只能查询菜单
    queryWrapper.eq("pid", permissionVo.getPermissionId());
    List<Permission> list = permissionService.list(queryWrapper);
    if (list.size() > 0) {
      map.put("value", true);
    } else {
      map.put("value", false);
    }
    return map;
  }

  /**
   * 删除菜单
   *
   * @param permissionVo
   * @return
   */
  @RequestMapping("deleteMenu")
  public ResultObj deleteMenu(PermissionVo permissionVo) {
    try {
      permissionService.removeById(permissionVo.getPermissionId());

      return ResultObj.DELETE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.DELETE_ERROR;
    }
  }
  /** 菜单管理结束* */
}
