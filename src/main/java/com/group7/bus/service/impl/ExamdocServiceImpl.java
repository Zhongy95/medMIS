package com.group7.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Examdoc;
import com.group7.bus.mapper.ExamdocMapper;
import com.group7.bus.service.ExamdocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 检查报告 服务实现类
 * </p>
 *
 * @author TT
 * @since 2020-06-20
 */
@Service
public class ExamdocServiceImpl extends ServiceImpl<ExamdocMapper, Examdoc> implements ExamdocService {
    @Override
    public IPage<Examdoc> getExamdocByPatientId(IPage<Examdoc> page, Integer pid) {
        return page.setRecords(this.baseMapper.getExamdocByPatientId(page, pid));
    }
}
