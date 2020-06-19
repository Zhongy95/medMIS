package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Exam;
import com.group7.bus.entity.Examtime;
import com.group7.bus.service.ExamService;
import com.group7.bus.service.ExamtimeService;
import com.group7.bus.vo.ExamtimeVo;
import com.group7.sys.common.DataGridView;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;

/**
 * <p>
 * 检验工作时间 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/api/bus/examtime")
@RequiresRoles(value={"PATIENT","DOCTOR"}, logical = Logical.OR)
public class ExamtimeController {

    private ExamService examService;
    private ExamtimeService examtimeService;

    @Autowired
    public ExamtimeController(ExamService examService, ExamtimeService examtimeService) {
        this.examService = examService;
        this.examtimeService = examtimeService;
    }

    @RequestMapping("loadExamtime")
    public DataGridView loadExamtime(ExamtimeVo examtimeVo) {
        IPage<Examtime> page = new Page<>(examtimeVo.getPage(), examtimeVo.getLimit());
        QueryWrapper<Examtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exam_id", examtimeVo.getExamId())
                .ne("remain", 0);
        this.examtimeService.page(page, queryWrapper);
        for (Examtime examtime:page.getRecords()){
            Exam exam = this.examService.getById(examtime.getExamId());
            examtime.setExamName(exam.getExamName());
        }
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}

