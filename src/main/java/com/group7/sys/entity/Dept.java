package com.group7.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
@TableName("sys_dept")
public class Dept implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "dept_id", type = IdType.AUTO)
  private Integer deptId;

  private Integer pid;

  private String deptName;

  private Boolean opened;

  private String remark;

  private String address;

  private Boolean available;

  /** 排序码【为了调试显示顺序】 */
  private Integer orderNum;

  private Date createTime;
}
