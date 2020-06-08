package com.group7.sys.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author lyh
 * @since 2020-06-05
 */
@RestController
@RequestMapping("/api/sys/role-permission/")
@RequiresRoles("ADMIN")
public class RolePermissionController {}
