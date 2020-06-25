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
 * 治疗报告
 * </p>
 *
 * @author TT
 * @since 2020-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_treatdoc")
public class Treatdoc implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 治疗报告文档id
     */
      @TableId(value = "treatdoc_id", type = IdType.AUTO)
    private Integer treatdocId;

    /**
     * 生成该报告的治疗单
     */
    private Integer treattodoId;

    /**
     * 护士的id
     */
    private Integer nurseId;

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
     * 治疗的结果
     */
    private String content;

    /**
     * 是否完成治疗
     */
    private Boolean ifdone;

    /**
     * 治疗项目名称
     */
    @TableField(exist = false)
    private String treatName;

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
     * 护士姓名
     */
    @TableField(exist = false)
    private String nurseName;
}
