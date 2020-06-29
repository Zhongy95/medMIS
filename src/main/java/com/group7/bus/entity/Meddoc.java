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
 * 药品报告
 * </p>
 *
 * @author TT
 * @since 2020-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_meddoc")
public class Meddoc implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 取药报告文档id
     */
      @TableId(value = "meddoc_id", type = IdType.AUTO)
    private Integer meddocId;


    /**
     * 开药医生的id
     */
    private Integer pharmacistId;

    /**
     * 生成该报告的病历
     */
    private Integer recordId;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否可用
     */
    private Boolean available;

    /**
     * 是否完成给药
     */
    private Boolean ifdone;

    /**
     * 是否选择快递
     */
    private Boolean ifdelivery;

    /**
     * 快递地址
     */
    private String deliveryaddr;

    /**
     * 药品名称
     */
    @TableField(exist = false)
    private String medName;

    /**
     * 患者姓名
     */
    @TableField(exist = false)
    private String patientName;

    /**
     * 患者id
     */
    @TableField(exist = false)
    private Integer patientId;

    /**
     * 药剂师姓名
     */
    @TableField(exist = false)
    private String pharmacistName;


}
