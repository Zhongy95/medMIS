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
 * 医生给患者开具的药物单
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_medtodo")
public class Medtodo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "medtodo_id", type = IdType.AUTO)
    private Integer medtodoId;

    /**
     * 创建该单的病历报告id
     */
    private Integer recordId;

    /**
     * 药品id
     */
    private Integer medId;

    private Date createtime;

    private Integer available;

    private Boolean payIfdone;

    private Integer registerId;

    /**
     * 药物名称
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
    private String patientId;
}
