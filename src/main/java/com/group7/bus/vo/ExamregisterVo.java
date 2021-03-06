package com.group7.bus.vo;

import com.group7.bus.entity.Examregister;
import com.group7.bus.entity.Examtime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExamregisterVo extends Examregister {

    private static final long serialVersionUID = 1L;

    private Integer Page = 1;

    private Integer limit = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
