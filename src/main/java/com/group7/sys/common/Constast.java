package com.group7.sys.common;

public interface Constast {

  /** 状态码 */
  public static final Integer OK = 200;

  public static final Integer ERROR = -1;

  /** 菜单权限类型 */
  public static final String TYPE_MENU = "menu";

  public static final String TYPE_PERMISSION = "permission";

  /** 可用状态 */
  public static final Boolean AVAILABLE_TRUE = true;

  public static final Boolean AVAILABLE_FALSE = false;

  /** 用户类型 */
  public static final Integer USER_TYPE_SUPER = 0;

  public static final Integer USER_TYPE_NORMAL = 1;

  /** 是否展开 */
  public static final Boolean OPEN_TRUE = true;

  public static final Boolean OPEN_FALSE = false;

  /*角色类型*/
  public static final Integer ROLE_ADMIN = 6;
  public static final Integer ROLE_DOCTOR = 1;
  public static final Integer ROLE_PHARMACIST = 2;
  public static final Integer ROLE_LABORATORIAN = 3;
  public static final Integer ROLE_NURSE = 4;
  public static final Integer ROLE_PATIENT = 5;

  /** 默认密码*/

  public static final String USER_DEFAULT_PWD = "123456";

  /** 缴费记录类型*/
  public static final Integer PAYMENT_REGISTER = 1;
  public static final Integer PAYMENT_EXAM = 2;
  public static final Integer PAYMENT_TREATMENT = 3;
  public static final Integer PAYMENT_MEDICINE = 4;

  /**队列状态*/
  public static final Integer QUEUE_INQUEUE = 1;
  public static final Integer QUEUE_INRECORD = 2;
  public static final Integer QUEUE_AFTERRECORD = 3;


}
