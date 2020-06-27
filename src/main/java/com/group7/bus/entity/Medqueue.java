package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 排队取药
 * </p>
 *
 * @author Robin
 * @since 2020-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_medqueue")
public class Medqueue implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 取药排队id
     */
      @TableId(value = "queue_id", type = IdType.AUTO)
    private Integer queueId;

    /**
     * 取药单id
     */
    private Integer medtodoId;

    /**
     * 是否可用
     */
    private Integer available;

    private Integer situation;

    private Data createTime;
    @TableField(exist = false)
    private String medName;

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
    private Integer queueNumber;


}
