package com.group7.bus.vo;

import com.group7.bus.entity.Register;
import com.group7.sys.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterVo extends Register {

  private static final long serialVersionUID = 1L;

  private Integer Page = 1;

//  private Integer[] ids;

  private Integer limit = 10;

  private Integer[] registerIds;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date startTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;

}
