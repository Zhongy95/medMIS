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
 * 检查报告
 * </p>
 *
 * @author TT
 * @since 2020-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_examdoc")
public class Examdoc implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 检查报告文档id
     */
      @TableId(value = "examdoc_id", type = IdType.AUTO)
    private Integer examdocId;

    /**
     * 生成该报告的检查单
     */
    private Integer examtodoId;

    /**
     * 检查医生的id
     */
    private Integer laboratorianId;

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
     * 检查的结果
     */
    private String content;

    /**
     * 检查是否完成
     */
    private Boolean ifdone;

    /**
     * 检查项目名称
     */
    @TableField(exist = false)
    private String examName;

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
     * 检验医师姓名
     */
    @TableField(exist = false)
    private String laboratorianName;
}
