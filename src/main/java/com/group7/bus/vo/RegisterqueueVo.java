package com.group7.bus.vo;

import com.group7.bus.entity.Register;
import com.group7.bus.entity.Registerqueue;
import com.group7.sys.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterqueueVo extends Registerqueue {

    private static final long serialVersionUID = 1L;

    private Integer Page = 1;

//  private Integer[] ids;

    private Integer limit = 10;

    private Integer[] queueIds;

}
