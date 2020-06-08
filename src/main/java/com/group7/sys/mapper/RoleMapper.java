package com.group7.sys.mapper;

import com.group7.sys.entity.Permission;
import com.group7.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author Robin
 * @since 2020-06-08
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {

  @Select(
      "SELECT role_code FROM sys_role\n"
          + "JOIN sys_user\n"
          + "ON sys_user.role_id = sys_role.role_id\n"
          + "WHERE sys_user.user_id = #{id}\n")
  List<String> getRoleByUserId(Integer id);
}
