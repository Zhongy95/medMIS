package com.group7.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyh
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "r_p_id", type = IdType.AUTO)
    private Integer rPId;

    private Integer roleId;

    private Integer permissionId;


}
