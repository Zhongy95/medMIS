package com.group7.bus.vo;

import com.group7.bus.entity.Examqueue;
import com.group7.bus.entity.Medqueue;
import com.group7.bus.entity.Treatqueue;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class MedqueueVo extends Medqueue {

    private static final long serialVersionUID = 1L;

    private Integer Page = 1;


    private Integer limit = 10;

    private Integer[] queueIds;
}