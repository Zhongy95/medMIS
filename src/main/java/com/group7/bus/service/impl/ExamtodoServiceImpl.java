package com.group7.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Examtodo;
import com.group7.bus.mapper.ExamtodoMapper;
import com.group7.bus.service.ExamtodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group7.bus.vo.ExamtodoVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生给患者开具的待检查项目单 服务实现类
 * </p>
 *
 * @author TT
 * @since 2020-06-16
 */
@Service
public class ExamtodoServiceImpl extends ServiceImpl<ExamtodoMapper, Examtodo> implements ExamtodoService {

    @Override
    public IPage<Examtodo> getExamtodoByPatientId(IPage<Examtodo> page, Integer pid) {
        return page.setRecords(this.baseMapper.getExamtodoByPatientId(page, pid));
    }

}
