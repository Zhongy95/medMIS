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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Meddoc> meddocList = this.meddocService.list();
        QueryWrapper<Meddoc> queryWrapperE = new QueryWrapper<>();
        queryWrapperE.ge(meddocVo.getStartTime() != null, "createtime", meddocVo.getStartTime())
                .le(meddocVo.getEndTime() != null, "createtime", meddocVo.getEndTime())
                .orderByDesc("createtime");
        for(Meddoc meddoc:meddocList)
        {
            Record medRecord = this.recordService.getById(meddoc.getRecordId());
            if(medRecord.getPatientId().equals(user.getUserId()))
            {
                initialMedicineRecord(medRecord);
                meddoc.setMedName(medRecord.getMedContent());
                meddoc.setPatientName(user.getName());
                User lab = userService.getById(meddoc.getPharmacistId());
                meddoc.setPharmacistName(lab.getName());
                if (meddocVo.getPharmacistName() != null) {
                    if (!meddoc.getPharmacistName().contains(meddocVo.getPharmacistName())) {
                        meddocList.remove(meddoc);
                    }
                }
            }
            else {
                meddocList.remove(meddoc);
            }
        }

        IPage<Meddoc> pageM = new Page<>(meddocVo.getPage(), meddocVo.getLimit());

        pageM.setRecords(meddocList);


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
            Record medRecord = this.recordService.getById(meddoc.getRecordId());
            initialMedicineRecord(medRecord);
            meddoc.setMedName(medRecord.getMedContent());
            User user = this.userService.getById(medRecord.getPatientId());
            meddoc.setPatientName(user.getName());
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

    private Record initialMedicineRecord(Record record){
        if(record.getIfdrug())
        {
            QueryWrapper<Medtodo> medtodoQueryWrapper =new QueryWrapper<>();
            medtodoQueryWrapper.eq("record_id",record.getRecordId());
            List<Medtodo> medtodoList = this.medtodoService.list(medtodoQueryWrapper);
            String medName = null;
            HashMap<Integer,Integer> recordMedMap=new HashMap<Integer, Integer>();
            boolean payIfdone = true;
            for(Medtodo medtodo:medtodoList){
                if(!recordMedMap.containsKey(medtodo.getMedId()))
                {
                    //如果单里没有此类药物，则放入，初始值为1
                    recordMedMap.put(medtodo.getMedId(),1);
                }
                else {
                    //如果单里已有此类药物，则+1
                    recordMedMap.put(medtodo.getMedId(),recordMedMap.get(medtodo.getMedId())+1);
                }
                //如果有一个未完成支付，则全单未完成支付
                if(!medtodo.getPayIfdone())
                    payIfdone =false;
            }
            record.setMedPayIfdone(payIfdone);
            record.setMedAvailable(medtodoList.get(0).getAvailable());
            String recordMedContent ="" ;
            for(Map.Entry<Integer, Integer> set :recordMedMap.entrySet()){
                Medicine medicine = this.medicineService.getById(set.getKey());
                if(medicine!=null)
                    recordMedContent=recordMedContent + " "+medicine.getMedName()+set.getValue()+"份";
            }
            record.setMedContent(recordMedContent);
        }
        return record;
    }


}

