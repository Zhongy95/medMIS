package com.group7.sys.vo;

import com.group7.sys.entity.Loginfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoVo extends Loginfo {

  private static final long serialVersionUID = 1L;

  private Integer Page = 1;

  private Integer[] ids;

  private Integer limit = 10;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;
}
