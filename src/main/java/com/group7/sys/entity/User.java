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
@TableName("sys_user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "user_id", type = IdType.AUTO)
  private Integer userId;

  /** 真实姓名 */
  private String name;

  private String loginname;

  private String password;

  /** 性别.1为男，0为女 */
  private Integer gender;

  /** 身份证号码 */
  private String idNum;

  /** 医保卡号 */
  private String medNum;

  /** 地址 */
  private String addr;

  /** 医生的部门id */
  private String deptId;

  /** 角色。0为管理员，1为门诊医生，2为药剂医生，3为检验医师，4为护士，5为病人 */
  private Integer roleId;

  /** 电话号码 */
  private String phone;

  /** 医生个人信息 */
  private String info;

  private Date birthday;

  /** 病人工作 */
  private String job;

  /** 1为可用，0为不可用 */
  private Boolean available;

  /** 医生职称 */
  private String jobTitle;
}
