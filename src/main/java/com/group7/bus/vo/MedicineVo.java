package com.group7.bus.vo;

import com.group7.bus.entity.Medicine;
import com.group7.bus.entity.Treatment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MedicineVo extends Medicine {

    private static final long serialVersionUID = 1L;

    private Integer registerId;

    private Integer Page = 1;

    private Integer limit = 6;

    private Integer[] ids;

}
