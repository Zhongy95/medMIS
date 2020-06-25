package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 排队治疗
 * </p>
 *
 * @author Robin
 * @since 2020-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_treatqueue")
public class Treatqueue implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 治疗排队id
     */
      @TableId(value = "queue_id", type = IdType.AUTO)
    private Integer queueId;

    /**
     * 治疗排队号
     */
    private Integer queueNumber;

    /**
     * 治疗单id
     */
    private Integer treattodoId;

    /**
     * 是否可用
     */
    private Integer available;

    @TableField(exist = false)
    private String treatmentName;

    @TableField(exist = false)
    private String patientName;

    @TableField(exist = false)
    private Integer patientId;
    @TableField(exist = false)
    private Integer registerId;
    @TableField(exist = false)
    private BigDecimal price;

    @TableField(exist = false)
    private Integer recordId;
    @TableField(exist = false)
    private Integer situation;

}
