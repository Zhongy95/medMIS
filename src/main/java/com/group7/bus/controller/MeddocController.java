package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Record;
import com.group7.bus.entity.Meddoc;
import com.group7.bus.entity.Medicine;
import com.group7.bus.entity.Medtodo;
import com.group7.bus.service.*;
import com.group7.bus.vo.MeddocVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 药品报告 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-27
 */
@RestController
@RequestMapping("/api/bus/meddoc")
@RequiresRoles(value={"PATIENT","DOCTOR","PHARMACIST"}, logical = Logical.OR)
public class MeddocController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MeddocService meddocService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @Autowired
    private MedtodoService medtodoService;


    /**
     * 查询-指定病人
     *
     * @param meddocVo
     * @return
     */
    @RequestMapping("loadMeddoc")
    @RequiresRoles("PATIENT")
    public DataGridView loadMeddoc(MeddocVo meddocVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Record> pagerR = new Page<>(meddocVo.getPage(), meddocVo.getLimit());
        QueryWrapper<Record> queryWrapperR = new QueryWrapper<>();
        queryWrapperR.eq("patient_id", user.getUserId());
        this.recordService.page(pagerR, queryWrapperR);
        Record record = pagerR.getRecords().get(0);

        IPage<Meddoc> pageM = new Page<>(meddocVo.getPage(), meddocVo.getLimit());
        QueryWrapper<Meddoc> queryWrapperE = new QueryWrapper<>();
        queryWrapperE.eq("record_id", record.getRecordId())
                .ge(meddocVo.getStartTime() != null, "createtime", meddocVo.getStartTime())
                .le(meddocVo.getEndTime() != null, "createtime", meddocVo.getEndTime())
                .orderByDesc("createtime"); // 排序依据
        this.meddocService.page(pageM, queryWrapperE);

        List<Meddoc> list = new ArrayList<>();
        for (Meddoc meddoc : pageM.getRecords())
            list.add(meddoc);

        for (Meddoc meddoc : pageM.getRecords()) {
            Medtodo medtodo = this.medtodoService.getById(meddoc.getMedtodoId());
            Medicine medicine = this.medicineService.getById(medtodo.getMedId());
            meddoc.setMedName(medicine.getMedName());
            meddoc.setPatientName(user.getName());
            User lab = userService.getById(meddoc.getPharmacistId());
            meddoc.setPharmacistName(lab.getName());

            if (meddocVo.getPharmacistName() != null) {
                if (!meddoc.getPharmacistName().contains(meddocVo.getPharmacistName())) {
                    list.remove(meddoc);
                }
            }
        }
        pageM.setRecords(list);

        return new DataGridView(pageM.getTotal(), pageM.getRecords());
    }

    /**
     * 删除
     *
     * @param meddocVo
     * @return
     *//*
    @RequestMapping("deleteMeddoc")
    public ResultObj deleteMeddoc(MeddocVo meddocVo) throws medMISException {
        try {
            meddocService.removeById(meddocVo.getMeddocId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    *//**
     * 批量删除
     *
     * @param meddocVo
     * @return
     *//*
    @RequestMapping("batchDeleteMeddoc")
    public ResultObj batchDeleteMeddoc(MeddocVo meddocVo) throws medMISException {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : meddocVo.getIds()) {
                idList.add(id);
            }
            meddocService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }*/

    /**
     * 查询-所有病人
     *
     * @param meddocVo
     * @return
     */
    @RequestMapping("loadAllMeddoc")
    @RequiresRoles("PHARMACIST")
    public DataGridView loadAllMeddoc(MeddocVo meddocVo) {
        IPage<Meddoc> page = new Page<>(meddocVo.getPage(), meddocVo.getLimit());
        QueryWrapper<Meddoc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createtime")
                .ge(meddocVo.getStartTime() != null, "createtime", meddocVo.getStartTime())
                .le(meddocVo.getEndTime() != null, "createtime", meddocVo.getEndTime());

        this.meddocService.page(page, queryWrapper);
        List<Meddoc> list = new ArrayList<>();
        for (Meddoc meddoc : page.getRecords())
            list.add(meddoc);

        for (Meddoc meddoc : page.getRecords()) {
            Medtodo medtodo = this.medtodoService.getById(meddoc.getMedtodoId());
            Medicine medicine = this.medicineService.getById(medtodo.getMedId());
            meddoc.setMedName(medicine.getMedName());
            Record record = this.recordService.getById(meddoc.getRecordId());
            User user = this.userService.getById(record.getPatientId());
            meddoc.setPatientName(user.getName());
            meddoc.setMedName(medicine.getMedName());
            User lab = userService.getById(meddoc.getPharmacistId());
            meddoc.setPharmacistName(lab.getName());

            if (meddocVo.getPatientName() != null) {
                if (!meddoc.getPatientName().contains(meddocVo.getPatientName())) {
                    list.remove(meddoc);
                }
            }
        }
        page.setRecords(list);

        return new DataGridView(page.getTotal(), page.getRecords());
    }

}

