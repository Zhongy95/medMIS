package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Exam;
import com.group7.bus.entity.Examdoc;
import com.group7.bus.entity.Examtodo;
import com.group7.bus.entity.Record;
import com.group7.bus.service.ExamService;
import com.group7.bus.service.ExamdocService;
import com.group7.bus.service.ExamtodoService;
import com.group7.bus.service.RecordService;
import com.group7.bus.vo.ExamdocVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


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

    /**
     * 查询-指定病人
     *
     * @param examdocVo
     * @return
     */
    @RequestMapping("loadExamdoc")
    public DataGridView loadExamdoc(ExamdocVo examdocVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Examdoc> page = examdocService.getExamdocByPatientId(
                new Page<>(examdocVo.getPage(), examdocVo.getLimit()), user.getUserId());

        for(Examdoc examdoc : page.getRecords()) {
            Examtodo examtodo = this.examtodoService.getById(examdoc.getExamtodoId());
            Exam exam = this.examService.getById(examtodo.getExamId());
            examdoc.setExamName(exam.getExamName());
            examdoc.setPatientName(user.getName());
            User lab = userService.getById(examdoc.getLaboratorianId());
            examdoc.setLaboratorianName(lab.getName());
        }

        return new DataGridView(page.getTotal(), page.getRecords());
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
    public DataGridView loadAllExamdoc(ExamdocVo examdocVo) {
        IPage<Examdoc> page = new Page<>(examdocVo.getPage(), examdocVo.getLimit());
        QueryWrapper<Examdoc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createtime");

        this.examdocService.page(page,queryWrapper);

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
        }

        return new DataGridView(page.getTotal(), page.getRecords());
    }

}


