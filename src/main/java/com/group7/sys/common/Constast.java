package com.group7.sys.common;

public interface Constast {

  /** 状态码 */
  public static final Integer OK = 200;
  public static final Integer ERROR = -1;

  /** 菜单权限类型 */
  public static final String TYPE_MENU = "menu";
  public static final String TYPE_PERMISSION = "permission";

  /** 可用状态*/
  public static final Object AVAILABLE_TRUE = 1;
  public static final Object AVAILABLE_FALSE = 0;

  /** 用户类型 */
  public static final Integer USER_TYPE_SUPER=0;
  public static final Integer USER_TYPE_NORMAL=1;

  /** 是否展开 */
  public static final Integer OPEN_TRUE=1;
  public static final Integer OPEN_FALSE=0;

  /*角色类型*/
  public static final Integer ROLE_ADMIN=0;
  public static final Integer ROLE_DOCTOR=1;
  public static final Integer ROLE_MEDICINED_DOCTOR=2;
  public static final Integer ROLE_EXAM_DOCTOR=3;
  public static final Integer ROLE_NURSE=4;
  public static final Integer ROLE_PATIENT=5;

}
