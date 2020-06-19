package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 检查预约单
 * </p>
 *
 * @author Robin
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_examregister")
public class Examregister implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 检查挂号id
     */
      @TableId(value = "examregister_id", type = IdType.AUTO)
    private Integer examregisterId;

    /**
     * 检查项目id
     */
    private Integer examId;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 付款单id
     */
    private Integer paymentId;

    /**
     * 是否付款
     */
    private Integer paymentIfdone;

    /**
     * 是否可用
     */
    private Integer available;

    /**
     * 检查时间id
     */
    private Integer examtimeId;

    /**
     * 检查单id
     */
    private Integer examtodoId;


    @TableField(exist = false)
    private String examName;

    @TableField(exist = false)
    private String patientName;


}
