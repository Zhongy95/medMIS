package com.group7.bus.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_medicine")
public class Medicine implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用于标识药品id
     */
      @TableId(value = "med_id", type = IdType.AUTO)
    private Integer medId;

    /**
     * 药品名称
     */
    private String medName;

    /**
     * 药品每份价格
     */
    private BigDecimal price;

    /**
     * 药品剩余数量
     */
    private Integer stock;


}
