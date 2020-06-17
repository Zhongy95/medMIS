package com.group7.bus.mapper;

import com.group7.bus.entity.Registerqueue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 排队看病 Mapper 接口
 * </p>
 *
 * @author Robin
 * @since 2020-06-17
 */
public interface RegisterqueueMapper extends BaseMapper<Registerqueue> {

    Integer update(String statement);
    Integer delete(String statement);

}
