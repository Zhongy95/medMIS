package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 排队看病
 * </p>
 *
 * @author Robin
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_registerqueue")
public class Registerqueue implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 挂号排队id
     */
      @TableId(value = "queue_id", type = IdType.AUTO)
    private Integer queueId;

    /**
     * 挂号单号
     */
    private Integer registerId;

    /**
     * 是否可用
     */
    private boolean available;

    /**
     * 问诊状态
     */
    private Integer situation;

    /**
     * 创建时间
     */
    private Date createTime;

    /*医生ID*/
    @TableField(exist = false)
    private Integer doctorId;
    /*医生姓名*/
    @TableField(exist = false)
    private String doctorName;
    /*病人姓名*/
    @TableField(exist = false)
    private String patientName;
    /*病人ID*/
    @TableField(exist = false)
    private Integer patientId;
    /*科室Id*/
    @TableField(exist = false)
    private Integer deptId;
    /*科室*/
    @TableField(exist = false)
    private String deptName;
    /*即时排序*/
    @TableField(exist = false)
    private Integer queueNum;


}
