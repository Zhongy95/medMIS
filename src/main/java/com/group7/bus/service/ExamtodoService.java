package com.group7.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Examtodo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group7.bus.vo.ExamtodoVo;

/**
 * <p>
 * 医生给患者开具的待检查项目单 服务类
 * </p>
 *
 * @author TT
 * @since 2020-06-16
 */
public interface ExamtodoService extends IService<Examtodo> {
    IPage<Examtodo> getExamtodoByPatientId(IPage<Examtodo> page, Integer pid);
}
