package com.group7.bus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
import com.group7.bus.vo.ExamtodoVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * 医生给患者开具的待检查项目单 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/api/bus/examtodo")
@RequiresRoles(value={"PATIENT","DOCTOR"}, logical = Logical.OR)
public class ExamtodoController {
    @Autowired private ExamService examService;

    @Autowired private ExamtodoService examtodoService;

    /**
     * 查询指定病人的所有待做检查信息
     *
     * @param examtodoVo
     * @return
     */
    @RequestMapping("loadAllExamtodo")
    public DataGridView loadAllExamtodo(ExamtodoVo examtodoVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Examtodo> page = examtodoService.getExamtodoByPatientId(
                new Page<>(examtodoVo.getPage(), examtodoVo.getLimit()), user.getUserId());

        for(Examtodo examtodo : page.getRecords()) {
            Exam exam = this.examService.getById(examtodo.getExamId());
            examtodo.setExamName(exam.getExamName());
            examtodo.setPatientName(user.getName());
        }

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     *
     * @param examtodoVo
     * @return
     */
    @RequestMapping("deleteExamtodo")
    public ResultObj deleteExamtodo(ExamtodoVo examtodoVo) throws medMISException {
        try {
            examtodoService.removeById(examtodoVo.getExamtodoId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 批量删除
     *
     * @param examtodoVo
     * @return
     */
    @RequestMapping("batchDeleteExamtodo")
    public ResultObj batchDeleteExamtodo(ExamtodoVo examtodoVo) throws medMISException {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : examtodoVo.getIds()) {
                idList.add(id);
            }
            examtodoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }
}

