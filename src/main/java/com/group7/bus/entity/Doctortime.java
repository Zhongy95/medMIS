package com.group7.bus.entity;

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
 * 医生工作时间
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_doctortime")
public class Doctortime implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "doctortime_id", type = IdType.AUTO)
    private Integer doctortimeId;

    private Integer doctorId;

    /**
     * 工作时段开始
     */
    private Date startime;

    /**
     * 工作时段结束
     */
    private Date endtime;

    /**
     * 余号
     */
    private Integer remain;

    /**
     * 挂号费用
     */
    private Float price;


}
