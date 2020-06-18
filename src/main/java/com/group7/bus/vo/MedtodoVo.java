package com.group7.bus.vo;

import com.group7.bus.entity.Medtodo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MedtodoVo extends Medtodo {
    private static final long serialVersionUID = 1L;

    private Integer Page = 1;

    private Integer limit = 10;

    private Integer[] ids;
}
