package com.group7.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Examdoc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.bus.entity.Examdoc;

/**
 * <p>
 * 检查报告 服务类
 * </p>
 *
 * @author TT
 * @since 2020-06-20
 */
public interface ExamdocService extends IService<Examdoc> {
    IPage<Examdoc> getExamdocByPatientId(IPage<Examdoc> page, Integer pid);
}
