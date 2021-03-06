package com.group7.bus.vo;

import com.group7.bus.entity.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RecordVo extends Record {

    private static final long serialVersionUID = 1L;

    private Integer queueId ;

    private Integer Page = 1;

    private Integer limit = 10;

    private Boolean ifdelivery;

    private String deliveryaddr;

}
