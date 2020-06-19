package com.group7.bus.vo;

import com.group7.bus.entity.Examtime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExamtimeVo extends Examtime {

    private static final long serialVersionUID = 1L;

    private Integer Page = 1;

    private Integer limit = 10;

    private Integer examtodoId;
}
