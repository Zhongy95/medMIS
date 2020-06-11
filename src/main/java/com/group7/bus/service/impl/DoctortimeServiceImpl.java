package com.group7.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Doctortime;
import com.group7.bus.mapper.DoctortimeMapper;
import com.group7.bus.service.DoctortimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.bus.vo.DoctortimeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 医生工作时间 服务实现类
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */
@Service
public class DoctortimeServiceImpl extends ServiceImpl<DoctortimeMapper, Doctortime> implements DoctortimeService {

    private DoctortimeMapper doctortimeMapper;

    @Autowired
    public DoctortimeServiceImpl(DoctortimeMapper doctortimeMapper) {
        this.doctortimeMapper = doctortimeMapper;
    }

    public IPage<DoctortimeVo> loadAllDoctortime(Map m, IPage<DoctortimeVo> page) {
        return doctortimeMapper.loadAllDoctortime(m, page);
    }
}
