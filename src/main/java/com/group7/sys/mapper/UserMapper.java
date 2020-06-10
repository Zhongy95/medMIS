package com.group7.sys.mapper;

import com.group7.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper 接口
 *
 * @author Robin
 * @since 2020-06-02
 */
@Component(value ="userMapper")
public interface UserMapper extends BaseMapper<User> {}
