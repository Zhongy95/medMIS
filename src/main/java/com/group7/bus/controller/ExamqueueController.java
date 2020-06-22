package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
import com.group7.bus.vo.ExamqueueVo;
import com.group7.bus.vo.RegisterqueueVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Dept;
import com.group7.sys.entity.User;
import com.group7.sys.service.DeptService;
import com.group7.sys.service.UserService;
import com.group7.sys.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.group7.sys.common.Constast.QUEUE_AFTERRECORD;

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

    @RequestMapping("loadAllExamqueuePatient")
    public DataGridView loadAllExamqueuePatient(ExamqueueVo examqueueVo) {
        IPage<Examqueue> page = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());
        QueryWrapper<Examqueue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");// 排序依据
        this.examqueueService.page(page, queryWrapper);
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Examqueue> resultPage = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());
        List<Examqueue> list = new ArrayList<Examqueue>();
//        Integer targetDoctorid =null;//设置要看的目标医生
//        for(Examqueue examqueue:page.getRecords()){
//            Examregister examregister = this.examregisterService.getById(examqueue.getExamregisterId());
//            if(user.getUserId().equals(examregister.getPatientId()))
//            {
//                if(targetDoctorid==null) {
//                    targetDoctorid = examregister.getExamDoctorId();
//                    break;
//                }
//
//            }
//        }
        Integer queuenumbernow = 0; //设置队列排序初始值为0
        for(Examqueue examqueue:page.getRecords()){
            Examregister examregister = examregisterService.getById(examqueue.getExamregisterId());
            Payment payment = paymentService.getById(examregister.getPaymentId());
            examqueue.setPatientId(payment.getPatientId());
            Exam exam = examService.getById(examregister.getExamId());
            examqueue.setExamName(exam.getExamName());
            examqueue.setPrice(exam.getPrice());
            queuenumbernow++;
            examqueue.setQueueNum(queuenumbernow);

//            examqueue.setExamDoctorId(examregister.getExamDoctorId());
//            if(examqueue.getDoctorId().equals(targetDoctorid)&& !examqueue.getSituation().equals(QUEUE_AFTERRECORD)){
            if(!examqueue.getSituation().equals(QUEUE_AFTERRECORD) && user.getUserId()==examqueue.getPatientId()){
                User userPatient = userService.getById(examqueue.getPatientId());
                examqueue.setPatientName(userPatient.getName());
//                User userDoctor = userService.getById(examqueue.getDoctorId());
//                examqueue.setDeptId(Integer.parseInt(userDoctor.getDeptId()));
//                Dept dept = deptService.getById(userDoctor.getDeptId());
//                examqueue.setDeptName((dept.getDeptName()));
//                examqueue.setDoctorName(userDoctor.getName());

                list.add(examqueue);
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
        QueryWrapper<Examqueue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");// 排序依据
        this.examqueueService.page(page, queryWrapper);
//        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Examqueue> resultPage = new Page<>(examqueueVo.getPage(), examqueueVo.getLimit());
        List<Examqueue> list = new ArrayList<Examqueue>();
//        Integer targetDoctorid =null;//设置要看的目标医生
//        for(Examqueue examqueue:page.getRecords()){
//            Examregister examregister = this.examregisterService.getById(examqueue.getExamregisterId());
//            if(user.getUserId().equals(examregister.getPatientId()))
//            {
//                if(targetDoctorid==null) {
//                    targetDoctorid = examregister.getExamDoctorId();
//                    break;
//                }
//
//            }
//        }
        Integer queuenumbernow = 0; //设置队列排序初始值为0
        for(Examqueue examqueue:page.getRecords()){
            Examregister examregister = examregisterService.getById(examqueue.getExamregisterId());
            Payment payment = paymentService.getById(examregister.getPaymentId());
            examqueue.setPatientId(payment.getPatientId());
            Exam exam = examService.getById(examregister.getExamId());
            examqueue.setExamName(exam.getExamName());
            examqueue.setPrice(exam.getPrice());
            Examtodo examtodo = examtodoService.getById(examregister.getExamtodoId());
            Register register = registerService.getById(examtodo.getRegisterId());
            User userPatient = userService.getById(register.getPatientId());
            examqueue.setPatientName(userPatient.getName());

//            examqueue.setExamDoctorId(examregister.getExamDoctorId());
//            if(examqueue.getDoctorId().equals(targetDoctorid)&& !examqueue.getSituation().equals(QUEUE_AFTERRECORD)){
            if(!examqueue.getSituation().equals(QUEUE_AFTERRECORD) ){
//                User userPatient = userService.getById(examqueue.getPatientId());
//                examqueue.setPatientName(userPatient.getName());
//                User userDoctor = userService.getById(examqueue.getDoctorId());
//                examqueue.setDeptId(Integer.parseInt(userDoctor.getDeptId()));
//                Dept dept = deptService.getById(userDoctor.getDeptId());
//                examqueue.setDeptName((dept.getDeptName()));
//                examqueue.setDoctorName(userDoctor.getName());
                queuenumbernow++;
                examqueue.setQueueNum(queuenumbernow);
                list.add(examqueue);
            }
        }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }
//    @RequestMapping("loadAllRegisterqueueDoctor")
//    public DataGridView loadAllRegisterqueueDoctor(RegisterqueueVo registerqueueVo) {
//        IPage<Registerqueue> page = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
//        QueryWrapper<Registerqueue> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByAsc("create_time");// 排序依据
//        this.registerqueueService.page(page, queryWrapper);
//        User user = (User) WebUtils.getSession().getAttribute("user");
//        IPage<Registerqueue> resultPage = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
//        List<Registerqueue> list = new ArrayList<Registerqueue>();
//        Integer queuenumbernow = 0; //设置队列排序初始值为0
//        for(Registerqueue registerqueue:page.getRecords()){
//            Register register = registerService.getById(registerqueue.getRegisterId());
//            registerqueue.setPatientId(register.getPatientId());
//            registerqueue.setDoctorId(register.getDoctorId());
//            if(user.getUserId().equals(registerqueue.getDoctorId())&& !registerqueue.getSituation().equals(QUEUE_AFTERRECORD))
//            {
//                User userPatient = userService.getById(registerqueue.getPatientId());
//                registerqueue.setPatientName(userPatient.getName());
//                User userDoctor = userService.getById(registerqueue.getDoctorId());
//                registerqueue.setDeptId(Integer.parseInt(userDoctor.getDeptId()));
//                Dept dept = deptService.getById(userDoctor.getDeptId());
//                registerqueue.setDeptName((dept.getDeptName()));
//                registerqueue.setDoctorName(userDoctor.getName());
//                list.add(registerqueue);
//                queuenumbernow++;
//                registerqueue.setQueueNum(queuenumbernow);
//            }
//        }
//        resultPage.setRecords(list);
//        resultPage.setTotal(page.getTotal());
//        resultPage.setPages(page.getPages());
//        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
//    }

}

