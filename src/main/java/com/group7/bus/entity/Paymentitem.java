package com.group7.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 缴费项目
 * </p>
 *
 * @author Robin
 * @since 2020-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_paymentitem")
public class Paymentitem implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "paymentitem_id", type = IdType.AUTO)
    private Integer paymentitemId;

    private String paymentitemName;


}
