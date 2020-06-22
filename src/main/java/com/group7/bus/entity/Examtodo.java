package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_examtodo")
public class Examtodo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "examtodo_id", type = IdType.AUTO)
    private Integer examtodoId;

    /**
     * 创建该检查单的病历报告id
     */
    private Integer recordId;

    /**
     * 检查项目id
     */
    private Integer examId;

    private Date createtime;

    private Integer available;

    private Integer registerId;


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
    private String patientId;

    @TableField(exist = false)
    private String examDoctorName;

    @TableField(exist = false)
    private Integer examDoctorId;

}
