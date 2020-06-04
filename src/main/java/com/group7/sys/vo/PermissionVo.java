package com.group7.sys.vo;

import com.group7.sys.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

  private static final long serialVersionUID = 1L;
}
