package com.group7.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Medtodo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 医生给患者开具的药物单 服务类
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
public interface MedtodoService extends IService<Medtodo> {
    IPage<Medtodo> getMedtodoByPatientId(IPage<Medtodo> page, Integer pid);
}
