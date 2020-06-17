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
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_treatment")
public class Treatment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用于标识治疗项目id
     */
      @TableId(value = "treatment_id", type = IdType.AUTO)
    private Integer treatmentId;

    /**
     * 治疗项目名称
     */
    private String treatmentName;

    /**
     * 每次治疗价格
     */
    private BigDecimal price;

    /**
     * 治疗项目已用次数
     */
    private Integer usageCount;


}
