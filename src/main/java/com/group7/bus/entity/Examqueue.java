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
 * 排队看病
 * </p>
 *
 * @author Robin
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_examqueue")
public class Examqueue implements Serializable {

    private static final long serialVersionUID=1L;


    /**
     * 检查队列id
     */
      @TableId(value = "queue_id", type = IdType.AUTO)
    private Integer queueId;

    /**
     * 检查挂号id
     */
    private Integer examregisterId;

    /**
     * 是否可用
     */
    private Boolean available;


    private Integer situation;

    @TableField(exist = false)
    private String patientName;

    @TableField(exist = false)
    private Integer patientId;

    @TableField(exist = false)
    private String examDoctorName;

    @TableField(exist = false)
    private Integer queueNum;

    @TableField(exist = false)
    private BigDecimal price;

    @TableField(exist = false)
    private String examName;

}
