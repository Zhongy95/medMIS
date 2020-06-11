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
 * 挂号单
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_register")
public class Register implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "register_id", type = IdType.AUTO)
    private Integer registerId;

    private Integer patientId;

    private Integer doctorId;

    private Date createtime;

    private Integer paymentId;

    private Boolean paymentIfdone;

    private Boolean available;

    /**医生姓名*/
    @TableField(exist = false)
    private String doctorName;


}
