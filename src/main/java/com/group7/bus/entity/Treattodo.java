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
 * 医生给患者开具的待检查项目单
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_treattodo")
public class Treattodo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "treattodo_id", type = IdType.AUTO)
    private Integer treattodoId;

    /**
     * 创建该治疗的病历报告id
     */
    private Integer recordId;

    /**
     * 治疗项目id
     */
    private Integer treatmentId;

    private Date createtime;

    private Boolean available;


    private Integer registerId;

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
    private String patientId;
}
