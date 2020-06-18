package com.group7.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Treattodo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 医生给患者开具的待检查项目单 服务类
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
public interface TreattodoService extends IService<Treattodo> {
    IPage<Treattodo> getTreattodoByPatientId(IPage<Treattodo> page, Integer pid);
}
