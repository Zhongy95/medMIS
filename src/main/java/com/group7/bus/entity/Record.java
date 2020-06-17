package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
     * 诊断详情
     */
    private String diagnosis;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 是否需要检查
     */
    private Integer ifexam;

    /**
     * 是否需要用药
     */
    private Integer ifdrug;

    /**
     * 是否需要治疗
     */
    private Integer iftreat;

    /**
     * 是否可用
     */
    private Integer available;

    /**
     * 是否完成整个治疗流程
     */
    private Integer ifdone;


}
