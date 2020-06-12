package com.group7.bus.vo;

import com.group7.bus.entity.Doctortime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DoctortimeVo extends Doctortime {

    private static final long serialVersionUID = 1L;

    private Integer deptId;

    private Integer pid;

    private String doctorName;

    private String deptName;

    private Integer Page = 1;

    private Integer limit = 10;
}
