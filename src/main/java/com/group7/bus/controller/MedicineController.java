package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Medicine;
import com.group7.bus.service.MedicineService;
import com.group7.bus.vo.MedicineVo;
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
@RequestMapping("/api/bus/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @RequestMapping("loadAllMedicine")
    public DataGridView loadAllMedicine(MedicineVo medicineVo) {
        IPage<Medicine> page = this.medicineService.page( new Page<>(medicineVo.getPage(), medicineVo.getLimit()));

        return new DataGridView(page.getTotal(), page.getRecords());
    }

}

