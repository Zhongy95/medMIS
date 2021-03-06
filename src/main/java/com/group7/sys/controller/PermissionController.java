package com.group7.sys.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author Robin
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/api/sys/permission/")
@RequiresRoles("ADMIN")
public class PermissionController {}
