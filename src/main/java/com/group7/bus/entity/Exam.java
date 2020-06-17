package com.group7.bus.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author TT
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_exam")
public class Exam implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用于标识检查项目id
     */
      @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;

    /**
     * 检查项目名称
     */
    private String examName;

    /**
     * 每次检查价格
     */
    private BigDecimal price;

    /**
     * 检查项目已用次数
     */
    private Integer usageCount;


}
