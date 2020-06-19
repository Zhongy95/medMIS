package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Treatment;
import com.group7.bus.service.TreatmentService;
import com.group7.bus.vo.TreatmentVo;
import com.group7.sys.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/api/bus/treatment")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping("loadAllTreatment")
    public DataGridView loadAllTreatment(TreatmentVo treatmentVo) {
        IPage<Treatment> page = this.treatmentService.page( new Page<>(treatmentVo.getPage(), treatmentVo.getLimit()));

        return new DataGridView(page.getTotal(), page.getRecords());
    }

}

