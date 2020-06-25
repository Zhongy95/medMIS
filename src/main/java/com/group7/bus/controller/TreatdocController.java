package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Treatment;
import com.group7.bus.entity.Treatdoc;
import com.group7.bus.entity.Treattodo;
import com.group7.bus.entity.Record;
import com.group7.bus.service.*;
import com.group7.bus.vo.TreatdocVo;
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
 * 治疗报告 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-24
 */
@RestController
@RequestMapping("/api/bus/treatdoc")
@RequiresRoles(value={"PATIENT","DOCTOR","NURSE"}, logical = Logical.OR)
public class TreatdocController {
    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatdocService treatdocService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @Autowired
    private TreattodoService treattodoService;

    /**
     * 查询-指定病人
     *
     * @param treatdocVo
     * @return
     */
    @RequestMapping("loadTreatdoc")
    @RequiresRoles("PATIENT")
    public DataGridView loadTreatdoc(TreatdocVo treatdocVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Record> pagerR = new Page<>(treatdocVo.getPage(), treatdocVo.getLimit());
        QueryWrapper<Record> queryWrapperR = new QueryWrapper<>();
        queryWrapperR.eq("patient_id", user.getUserId());
        this.recordService.page(pagerR, queryWrapperR);
        Record record = pagerR.getRecords().get(0);

        IPage<Treatdoc> pageT = new Page<>(treatdocVo.getPage(), treatdocVo.getLimit());
        QueryWrapper<Treatdoc> queryWrapperE = new QueryWrapper<>();
        queryWrapperE.eq("record_id", record.getRecordId())
                .ge(treatdocVo.getStartTime() != null, "createtime", treatdocVo.getStartTime())
                .le(treatdocVo.getEndTime() != null, "createtime", treatdocVo.getEndTime())
                .orderByDesc("createtime"); // 排序依据
        this.treatdocService.page(pageT, queryWrapperE);

        List<Treatdoc> list = new ArrayList<>();
        for (Treatdoc treatdoc : pageT.getRecords())
            list.add(treatdoc);

        for (Treatdoc treatdoc : pageT.getRecords()) {
            Treattodo treattodo = this.treattodoService.getById(treatdoc.getTreattodoId());
            Treatment treatment = this.treatmentService.getById(treattodo.getTreatmentId());
            treatdoc.setTreatName(treatment.getTreatmentName());
            treatdoc.setPatientName(user.getName());
            User lab = userService.getById(treatdoc.getNurseId());
            treatdoc.setNurseName(lab.getName());

            if (treatdocVo.getNurseName() != null) {
                if (!treatdoc.getNurseName().contains(treatdocVo.getNurseName())) {
                    list.remove(treatdoc);
                }
            }
        }
        pageT.setRecords(list);

        return new DataGridView(pageT.getTotal(), pageT.getRecords());
    }

    /**
     * 删除
     *
     * @param treatdocVo
     * @return
     *//*
    @RequestMapping("deleteTreatdoc")
    public ResultObj deleteTreatdoc(TreatdocVo treatdocVo) throws medMISException {
        try {
            treatdocService.removeById(treatdocVo.getTreatdocId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    *//**
     * 批量删除
     *
     * @param treatdocVo
     * @return
     *//*
    @RequestMapping("batchDeleteTreatdoc")
    public ResultObj batchDeleteTreatdoc(TreatdocVo treatdocVo) throws medMISException {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : treatdocVo.getIds()) {
                idList.add(id);
            }
            treatdocService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }*/

    /**
     * 查询-所有病人
     *
     * @param treatdocVo
     * @return
     */
    @RequestMapping("loadAllTreatdoc")
    @RequiresRoles("NURSE")
    public DataGridView loadAllTreatdoc(TreatdocVo treatdocVo) {
        IPage<Treatdoc> page = new Page<>(treatdocVo.getPage(), treatdocVo.getLimit());
        QueryWrapper<Treatdoc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createtime")
                .ge(treatdocVo.getStartTime() != null, "createtime", treatdocVo.getStartTime())
                .le(treatdocVo.getEndTime() != null, "createtime", treatdocVo.getEndTime());

        this.treatdocService.page(page, queryWrapper);
        List<Treatdoc> list = new ArrayList<>();
        for (Treatdoc treatdoc : page.getRecords())
            list.add(treatdoc);

        for (Treatdoc treatdoc : page.getRecords()) {
            Treattodo treattodo = this.treattodoService.getById(treatdoc.getTreattodoId());
            Treatment treatment = this.treatmentService.getById(treattodo.getTreatmentId());
            treatdoc.setTreatName(treatment.getTreatmentName());
            Record record = this.recordService.getById(treatdoc.getRecordId());
            User user = this.userService.getById(record.getPatientId());
            treatdoc.setPatientName(user.getName());
            treatdoc.setTreatName(treatment.getTreatmentName());
            User lab = userService.getById(treatdoc.getNurseId());
            treatdoc.setNurseName(lab.getName());

            if (treatdocVo.getPatientName() != null) {
                if (!treatdoc.getPatientName().contains(treatdocVo.getPatientName())) {
                    list.remove(treatdoc);
                }
            }
        }
        page.setRecords(list);

        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
