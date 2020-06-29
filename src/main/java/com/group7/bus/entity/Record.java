package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 病历记录
 * </p>
 *
 * @author Robin
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_record")
public class Record implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用于标识唯一病历id
     */
      @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 病人id
     */
    private Integer patientId;

    /**
     * 医生id
     */
    private Integer doctorId;

    /**
     * 挂号id
     */
    private Integer registerId;

    /**
     * 诊断详情
     */
    private String diagnosis;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 是否需要检查
     */
    private Boolean ifexam;

    /**
     * 是否需要用药
     */
    private Boolean ifdrug;

    /**
     * 是否需要治疗
     */
    private Boolean iftreat;

    /**
     * 是否可用
     */
    private Boolean available;

    /**
     * 是否完成整个治疗流程
     */
    private Boolean ifdone;

    @TableField(exist = false)
    private String doctorName;

    @TableField(exist = false)
    private String patientName;

    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date startTime;

    @TableField(exist = false)
    private HashMap<Integer,Integer> medtodoList;

    @TableField(exist = false)
    private String medContent;

    //是否可以取药
    @TableField(exist = false)
    private Boolean medAvailable;

    //是否完成支付
    @TableField(exist = false)
    private Boolean medPayIfdone;
}
