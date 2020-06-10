package com.group7.sys.controller;

import com.group7.sys.common.DataGridView;
import com.group7.sys.common.TreeNode;
import com.group7.sys.entity.Role;
import com.group7.sys.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端控制器
 *
 * @author Robin
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/api/sys/role/")
@RequiresRoles("ADMIN")
public class RoleController {

    @Autowired private RoleService roleService;

    /**
     * 加载用户类型树
     * @return
     */
    @RequestMapping("loadRoleManagerLeftTreeJson")
    public DataGridView loadRoleManagerLeftTreeJson() {
        List<Role> list = this.roleService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Role role : list) {
            if(role.getRoleId()!=5)
            {
                Boolean spread = false;
                treeNodes.add(new TreeNode(role.getRoleId(), 0, role.getRoleName(), spread));
            }
        }
        return new DataGridView(treeNodes);
    }
}
