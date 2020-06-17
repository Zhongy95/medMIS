package com.group7.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Treattodo;
import com.group7.bus.mapper.TreattodoMapper;
import com.group7.bus.service.TreattodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生给患者开具的待检查项目单 服务实现类
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@Service
public class TreattodoServiceImpl extends ServiceImpl<TreattodoMapper, Treattodo> implements TreattodoService {
    @Override
    public IPage<Treattodo> getTreattodoByPatientId(IPage<Treattodo> page, Integer pid) {
        return page.setRecords(this.baseMapper.getTreattodoByPatientId(page, pid));
    }
}
