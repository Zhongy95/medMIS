package com.group7.sys.mapper;

import com.group7.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author Robin
 * @since 2020-06-02
 */

@Component
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT * FROM sys_permission\n" +
            "JOIN sys_role_permission\n" +
            "ON sys_permission.permission_id = sys_role_permission.permission_id\n" +
            "WHERE role_id = #{role_id}\n")
    public List<Permission> getMenu(Integer role_id);
}
