package com.group7.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Robin
 * @since 2020-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "permission_id", type = IdType.AUTO)
  private Integer permissionId;

  private Integer pid;

  /** 权限类型[menu/permission] */
  private String type;

  private String title;

  /** 权限编码[只有type= permission才有 user:view] */
  private String perCode;

  private String icon;

  private String href;

  private String target;

  private Boolean opened;

  private Integer orderNum;

  private Boolean available;
}
