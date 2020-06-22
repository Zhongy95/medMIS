package com.group7.bus.vo;

import com.group7.bus.entity.Examdoc;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExamdocVo extends Examdoc {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;

    private Integer limit = 10;

}
