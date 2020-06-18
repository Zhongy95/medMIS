package com.group7.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Medtodo;
import com.group7.bus.mapper.MedtodoMapper;
import com.group7.bus.service.MedtodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生给患者开具的药物单 服务实现类
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@Service
public class MedtodoServiceImpl extends ServiceImpl<MedtodoMapper, Medtodo> implements MedtodoService {
    @Override
    public IPage<Medtodo> getMedtodoByPatientId(IPage<Medtodo> page, Integer pid) {
        return page.setRecords(this.baseMapper.getMedtodoByPatientId(page, pid));
    }
}
