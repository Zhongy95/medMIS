package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Exam;
import com.group7.bus.service.ExamService;

import com.group7.bus.vo.ExamVo;
import com.group7.sys.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/api/bus/exam")
public class ExamController {

    @Autowired private ExamService examService;

    @RequestMapping("loadAllExam")
    public DataGridView loadAllExam(ExamVo examVo) {
        IPage<Exam> page = this.examService.page( new Page<>(examVo.getPage(), examVo.getLimit()));

        return new DataGridView(page.getTotal(), page.getRecords());
    }
    

}

