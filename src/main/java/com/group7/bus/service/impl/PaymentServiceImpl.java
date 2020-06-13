package com.group7.bus.service.impl;

import com.group7.bus.entity.Payment;
import com.group7.bus.mapper.PaymentMapper;
import com.group7.bus.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 缴费单 服务实现类
 * </p>
 *
 * @author TT
 * @since 2020-06-12
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
