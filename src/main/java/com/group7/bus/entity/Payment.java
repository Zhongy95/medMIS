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
 * 缴费单
 * </p>
 *
 * @author TT
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_payment")
public class Payment implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;

    /**
     * 缴费项目编号
     */
    private Integer paymentitemId;

    /**
     * 缴费患者id
     */
    private Integer patientId;

    /**
     * 缴费备注
     */
    private String info;

    /**
     * 缴费单创建时间
     */
    private Date createtime;

    /**
     * 缴费完成时间
     */
    private Date donetime;

    /**
     * 是否可用，默认1
     */
    private Integer available;

    /**
     * 缴费项目金额
     */
    private Float amount;

    /**
     * 缴费是否完成
     */
    private Boolean ifdone;

    /**
     * 缴费项目名称
     */
    @TableField(exist = false)
    private String paymentitemName;

    /**
     * 缴费病人名称
     */
    @TableField(exist = false)
    private String patientName;


}
