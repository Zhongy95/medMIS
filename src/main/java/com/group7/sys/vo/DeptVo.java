package com.group7.sys.vo;

import com.group7.sys.entity.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

  private static final long serialVersionUID = 1L;

  private Integer Page = 1;

  private Integer limit = 10;
}
