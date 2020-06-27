package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.entity.Record;
import com.group7.bus.service.*;
import com.group7.bus.vo.ExamqueueVo;
import com.group7.bus.vo.ExamregisterVo;
import com.group7.bus.vo.RegisterVo;
import com.group7.bus.vo.RegisterqueueVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Dept;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.DeptService;
import com.group7.sys.service.UserService;
import com.group7.sys.service.impl.DeptServiceImpl;
import com.group7.sys.vo.UserVo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.group7.sys.common.Constast.*;

/**
 * <p>
 * 排队看病 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/api/bus/examqueue")
public class ExamqueueController {
    @Autowired
    private DeptService deptService;
    @Autowired private RegisterqueueService registerqueueService;
    @Autowired private RegisterService registerService;
    @Autowired private UserService userService;
    @Autowired private PaymentService paymentService;
    @Autowired private ExamService examService;
    @Autowired private ExamqueueService examqueueService;
    @Autowired private ExamregisterService examregisterService;
    @Autowired private ExamtodoService examtodoService;
    @Autowired private ExamdocService examdocService;
    @Autowired private RecordService recordService;

    @RequestMapping("loadAllExamqueuePatient")
    public DataGridView loadAllExamqueuePatient(ExamqueueVo examqueueVo) {
        IPage<Examqueue> page = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());

        IPage<Examqueue> resultPage = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());
        List<Examqueue> list = new ArrayList<Examqueue>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        //每个种类的检查都查询一遍
        List<Exam> examList = this.examService.list();
        for(Exam targetexam :examList){
            QueryWrapper<Examqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.examqueueService.page(page, queryWrapper);
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Examqueue examqueue:page.getRecords()){
                if(examqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
                Examregister examregister = examregisterService.getById(examqueue.getExamregisterId());
                Exam exam = examService.getById(examregister.getExamId());
                if (exam.getExamId()!=targetexam.getExamId()){continue;}//如果不属于该类，直接进下一个循环
                Payment payment = paymentService.getById(examregister.getPaymentId());
                examqueue.setPatientId(payment.getPatientId());
                examqueue.setExamName(exam.getExamName());
                examqueue.setPrice(exam.getPrice());
                queuenumbernow++;
                examqueue.setQueueNum(queuenumbernow);
                if(!examqueue.getSituation().equals(QUEUE_AFTERRECORD) && user.getUserId().equals(examqueue.getPatientId())){
                    //如果不是目标病人的，就查询不到
                    User userPatient = userService.getById(examqueue.getPatientId());
                    examqueue.setPatientName(userPatient.getName());
                    list.add(examqueue);
                }
            }
        }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }
//

    @RequestMapping("loadAllExamqueueDoctor")
    public DataGridView loadAllExamqueueDoctor(ExamqueueVo examqueueVo) {
        IPage<Examqueue> page = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());

        IPage<Examqueue> resultPage = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());
        List<Examqueue> list = new ArrayList<Examqueue>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        //每个种类的检查都查询一遍
        List<Exam> examList = this.examService.list();
        for(Exam targetexam :examList){
            QueryWrapper<Examqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.examqueueService.page(page, queryWrapper);
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Examqueue examqueue:page.getRecords()){
                if(examqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
                Examregister examregister = examregisterService.getById(examqueue.getExamregisterId());
                Exam exam = examService.getById(examregister.getExamId());
                if (!exam.getExamId().equals(targetexam.getExamId())){continue;}//如果不属于该类，直接进下一个循环
                Payment payment = paymentService.getById(examregister.getPaymentId());
                examqueue.setPatientId(payment.getPatientId());
                examqueue.setExamName(exam.getExamName());
                examqueue.setPrice(exam.getPrice());
                queuenumbernow++;
                examqueue.setQueueNum(queuenumbernow);
                if(!examqueue.getSituation().equals(QUEUE_AFTERRECORD)){
                    //如果不是目标病人的，就查询不到
                    User userPatient = userService.getById(examqueue.getPatientId());
                    examqueue.setPatientName(userPatient.getName());
                    list.add(examqueue);
                }
            }
        }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }


    @RequestMapping("addExamqueue")
    public ResultObj addExamqueue(ExamregisterVo examregisterVo) throws medMISException {
        try {
            Examregister registerin = this.examregisterService.getById(examregisterVo);
            if (!registerin.getAvailable())
                throw new medMISException("添加失败", HttpStatus.FORBIDDEN);
            if (!registerin.getPaymentIfdone())
                throw new medMISException("添加失败", HttpStatus.BAD_REQUEST);
            Examqueue examqueue = new Examqueue();

            examqueue.setExamregisterId(registerin.getExamregisterId());
            IPage<Examqueue> page = new Page<>();
            QueryWrapper<Examqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            //queryWrapper.last("limit 1");
            this.examqueueService.page(page, queryWrapper);//获得排最后的一条记录

            this.examqueueService.save(examqueue);
            registerin.setAvailable(false);//完成后，将可用状态改为否
            this.examregisterService.updateById(registerin);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("ExamRegisterQueue")
    @RequiresRoles("LABORATORIAN")
    public ResultObj ExamRegisterQueue (ExamqueueVo examqueueVo) throws medMISException {
        try {
            Examqueue examqueue = this.examqueueService.getById(examqueueVo);
            if(!examqueue.getAvailable())
                throw new medMISException("失效，无法添加", HttpStatus.FORBIDDEN);
            if(!examqueue.getSituation().equals(QUEUE_INQUEUE))
                throw new medMISException("不在排队状态", HttpStatus.BAD_REQUEST);
            examqueue.setSituation(QUEUE_INRECORD);

            this.examqueueService.updateById(examqueue);
            //检测是否有待办的检查报告
            User laboratorian = (User) WebUtils.getSession().getAttribute("user");
            QueryWrapper<Examdoc> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("laboratorian_id",laboratorian.getUserId());
            queryWrapper.eq("ifdone",false);
            List<Examdoc> examdocList =this.examdocService.list(queryWrapper);
            if(examdocList.size()!=0)
                throw new medMISException("还有待检查项目未完成，无法检查新项目", HttpStatus.CONFLICT);
            //新建检查报告，ifdone设置为未完成
            Examdoc examdoc =new Examdoc();
            examdoc.setPatientId(examqueue.getPatientId());
            examdoc.setLaboratorianId(laboratorian.getUserId());
            Examregister examregister = this.examregisterService.getById(examqueue.getExamregisterId());
            Examtodo examtodo = this.examtodoService.getById(examregister.getExamtodoId());
            examdoc.setExamtodoId(examtodo.getExamtodoId());
            examdoc.setRecordId(examtodo.getRecordId());
            examdoc.setCreatetime(new Date());
            examdoc.setIfdone(false);
            this.examdocService.save(examdoc);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping("loadUndoneExamDoc")
    public Examdoc loadUndoneExamDoc() throws medMISException {
        try {
            User user = (User) WebUtils.getSession().getAttribute("user");
            QueryWrapper<Examdoc> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("createtime");
            queryWrapper.eq("ifdone",false);
            queryWrapper.eq("laboratorian_id",user.getUserId());
            List<Examdoc> list = this.examdocService.list(queryWrapper);
            if(list.size()>1)
                throw new medMISException("有多个待办检查", HttpStatus.FORBIDDEN);
            Examdoc examdoc=list.get(0);

            Examtodo examtodo=this.examtodoService.getById(examdoc.getExamtodoId());
            Record record = this.recordService.getById(examtodo.getRecordId());
            User patient = this.userService.getById(record.getPatientId());
            examdoc.setPatientId(user.getUserId());
            examdoc.setPatientName(patient.getName());
            examdoc.setLaboratorianName(user.getName());
            Exam exam = this.examService.getById(examtodo.getExamId());
            examdoc.setExamName(exam.getExamName());

            return examdoc;
        } catch (Exception e) {
            throw new medMISException("读取失败", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("loadPatient")
    @RequiresRoles("LABORATORIAN")
    public User loadPatient(UserVo userVo) throws medMISException {
        try {
            return this.userService.getById(userVo);
        } catch (Exception e) {
            throw new medMISException("读取病人信息失败", HttpStatus.BAD_REQUEST);
        }
    }


}

