package com.group7.bus.vo;

import com.group7.bus.entity.Examtodo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExamtodoVo extends Examtodo {

    private static final long serialVersionUID = 1L;

    private Integer Page = 1;

    private Integer limit = 10;

    private Integer[] ids;

}
