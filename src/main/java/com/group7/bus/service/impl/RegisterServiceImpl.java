package com.group7.bus.service.impl;

import com.group7.bus.entity.Register;
import com.group7.bus.mapper.RegisterMapper;
import com.group7.bus.service.RegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 挂号单 服务实现类
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */
@Service

public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements RegisterService {

}
