package com.group7.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Robin
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer roleId;

  private String roleCode;
}
