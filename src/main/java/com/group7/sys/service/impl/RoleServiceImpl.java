package com.group7.sys.service.impl;

import com.group7.sys.entity.Role;
import com.group7.sys.mapper.RoleMapper;
import com.group7.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author Robin
 * @since 2020-06-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  private RoleMapper roleMapper;

  @Autowired
  public RoleServiceImpl(RoleMapper roleMapper) {
    this.roleMapper = roleMapper;
  }

  public List<String> getRoleByUserId(Integer id) {
    return roleMapper.getRoleByUserId(id);
  }
}
