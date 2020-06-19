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
 * 检验工作时间
 * </p>
 *
 * @author Robin
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_examtime")
public class Examtime implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 检查时间id
     */
      @TableId(value = "examtime_id", type = IdType.AUTO)
    private Integer examtimeId;

    /**
     * 检查项目id
     */
    private Integer examId;

    /**
     * 开始时间
     */
    private Date startime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 余号
     */
    private Integer remain;

    @TableField(exist = false)
    private String examName;
}
