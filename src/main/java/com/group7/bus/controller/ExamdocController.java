package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
import com.group7.bus.vo.ExamdocVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.group7.sys.common.Constast.QUEUE_AFTERRECORD;


/**
 * <p>
 * 检查报告 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-20
 */
@RestController
@RequestMapping("/api/bus/examdoc")
@RequiresRoles(value={"PATIENT","DOCTOR","LABORATORIAN"}, logical = Logical.OR)
public class ExamdocController {
    @Autowired private ExamService examService;

    @Autowired private ExamdocService examdocService;

    @Autowired private RecordService recordService;

    @Autowired private UserService userService;

    @Autowired private ExamtodoService examtodoService;

    @Autowired private ExamregisterService examregisterService;

    @Autowired private ExamqueueService examqueueService;

    /**
     * 查询-指定病人
     *
     * @param examdocVo
     * @return
     */
    @RequestMapping("loadExamdoc")
    @RequiresRoles("PATIENT")
    public DataGridView loadExamdoc(ExamdocVo examdocVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Record> pagerR = new Page<>(examdocVo.getPage(), examdocVo.getLimit());
        QueryWrapper<Record> queryWrapperR = new QueryWrapper<>();
        queryWrapperR.eq("patient_id", user.getUserId());
        this.recordService.page(pagerR, queryWrapperR);
        Record record = pagerR.getRecords().get(0);

        IPage<Examdoc> pageE = new Page<>(examdocVo.getPage(), examdocVo.getLimit());
        QueryWrapper<Examdoc> queryWrapperE = new QueryWrapper<>();
        queryWrapperE.eq("record_id", record.getRecordId())
            .ge(examdocVo.getStartTime() != null, "createtime", examdocVo.getStartTime())
            .le(examdocVo.getEndTime() != null, "createtime", examdocVo.getEndTime())
            .orderByDesc("createtime"); // 排序依据
        this.examdocService.page(pageE, queryWrapperE);

        List<Examdoc> list = new ArrayList<>();
        for(Examdoc examdoc : pageE.getRecords())
            list.add(examdoc);

        for(Examdoc examdoc : pageE.getRecords()) {
            Examtodo examtodo = this.examtodoService.getById(examdoc.getExamtodoId());
            Exam exam = this.examService.getById(examtodo.getExamId());
            examdoc.setExamName(exam.getExamName());
            examdoc.setPatientName(user.getName());
            User lab = userService.getById(examdoc.getLaboratorianId());
            examdoc.setLaboratorianName(lab.getName());

            if(examdocVo.getLaboratorianName() != null){
                if(!examdoc.getLaboratorianName().contains(examdocVo.getLaboratorianName())){
                    list.remove(examdoc);
                }
            }
        }
        pageE.setRecords(list);

        return new DataGridView(pageE.getTotal(), pageE.getRecords());
    }

    /**
     * 删除
     *
     * @param examdocVo
     * @return
     *//*
    @RequestMapping("deleteExamdoc")
    public ResultObj deleteExamdoc(ExamdocVo examdocVo) throws medMISException {
        try {
            examdocService.removeById(examdocVo.getExamdocId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    *//**
     * 批量删除
     *
     * @param examdocVo
     * @return
     *//*
    @RequestMapping("batchDeleteExamdoc")
    public ResultObj batchDeleteExamdoc(ExamdocVo examdocVo) throws medMISException {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : examdocVo.getIds()) {
                idList.add(id);
            }
            examdocService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }*/

    /**
     * 查询-所有病人
     *
     * @param examdocVo
     * @return
     */
    @RequestMapping("loadAllExamdoc")
    @RequiresRoles("LABORATORIAN")
    public DataGridView loadAllExamdoc(ExamdocVo examdocVo) {
        IPage<Examdoc> page = new Page<>(examdocVo.getPage(), examdocVo.getLimit());
        QueryWrapper<Examdoc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createtime")
            .ge(examdocVo.getStartTime() != null, "createtime", examdocVo.getStartTime())
            .le(examdocVo.getEndTime() != null, "createtime", examdocVo.getEndTime());

        this.examdocService.page(page,queryWrapper);
        List<Examdoc> list = new ArrayList<>();
        for(Examdoc examdoc : page.getRecords())
            list.add(examdoc);

        for(Examdoc examdoc : page.getRecords()) {
            Examtodo examtodo = this.examtodoService.getById(examdoc.getExamtodoId());
            Exam exam = this.examService.getById(examtodo.getExamId());
            examdoc.setExamName(exam.getExamName());
            Record record = this.recordService.getById(examdoc.getRecordId());
            User user = this.userService.getById(record.getPatientId());
            examdoc.setPatientName(user.getName());
            examdoc.setExamName(exam.getExamName());
            User lab = userService.getById(examdoc.getLaboratorianId());
            examdoc.setLaboratorianName(lab.getName());

            if(examdocVo.getPatientName() != null){
                if(!examdoc.getPatientName().contains(examdocVo.getPatientName())){
                    list.remove(examdoc);
                }
            }
        }
        page.setRecords(list);

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @RequestMapping("UpdateExamdoc")
    @RequiresRoles("LABORATORIAN")
    public ResultObj UpdateExamdoc(ExamdocVo examdocVo) throws medMISException {
        try {
            this.examdocService.updateById(examdocVo);
            if(examdocVo.getIfdone()!=null){
                if(examdocVo.getIfdone())
                {
                    //如果完成检查，则更改队列状态
                    QueryWrapper<Examregister> examregisterQueryWrapper =new QueryWrapper<>();
                    examregisterQueryWrapper.eq("examtodo_id",examdocVo.getExamtodoId());
                    Examregister targetExamRegister = this.examregisterService.getOne(examregisterQueryWrapper);
                    Exam exam = this.examService.getById(targetExamRegister.getExamId());
                    //完成了之后，使用次数+1
                    exam.setUsageCount(exam.getUsageCount()+1);
                    this.examService.updateById(exam);
                    QueryWrapper<Examqueue>queryWrapper=new QueryWrapper<>();
                    queryWrapper.eq("examregister_id",targetExamRegister.getExamregisterId());
                    Examqueue examqueue = this.examqueueService.getOne(queryWrapper);
                    examqueue.setSituation(QUEUE_AFTERRECORD);
                    this.examqueueService.updateById(examqueue);
                }
            }
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("更新失败", HttpStatus.UNAUTHORIZED);
        }
    }

}


