package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
import com.group7.bus.vo.ExamregisterVo;
import com.group7.bus.vo.ExamtimeVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.group7.sys.common.Constast.PAYMENT_EXAM;

/**
 * <p>
 * 检查预约单 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/api/bus/examregister")
public class ExamregisterController {

    private ExamService examService;
    private ExamtimeService examtimeService;
    private ExamregisterService examregisterService;
    private ExamtodoService examtodoService;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private PaymentService paymentService;
    private UserService userService;


    @Autowired
    public ExamregisterController(ExamService examService, ExamtimeService examtimeService, ExamregisterService examregisterService, ExamtodoService examtodoService, PaymentService paymentService, UserService userService) {
        this.examService = examService;
        this.examtimeService = examtimeService;
        this.examregisterService = examregisterService;
        this.examtodoService = examtodoService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @RequestMapping("addRegister")
    @RequiresRoles("PATIENT")
    public ResultObj addRegister(ExamtimeVo examtimeVo) throws medMISException {
        try {
            Examregister registeradd = new Examregister();
            registeradd.setExamtimeId(examtimeVo.getExamtimeId());
            registeradd.setExamId(examtimeVo.getExamId());
            Exam exam = examService.getById(registeradd.getExamId());
            registeradd.setExamName(exam.getExamName());
            registeradd.setCreatetime(new Date());
            registeradd.setExamtodoId(examtimeVo.getExamtodoId());

            //挂号提交的同时新建缴费单
            User patient = (User) WebUtils.getSession().getAttribute("user");
            Payment paymentnew = new Payment();
            paymentnew.setAmount(exam.getPrice().floatValue());
            paymentnew.setPatientId(patient.getUserId());
            String content = "挂号项目为"+exam.getExamName()+",挂号时间为"+simpleDateFormat.format(examtimeVo.getStartime())+"~"+simpleDateFormat.format(examtimeVo.getEndtime());
            paymentnew.setInfo(content);
            paymentnew.setPaymentitemId(PAYMENT_EXAM);
            paymentnew.setCreatetime(new Date());
            this.paymentService.save(paymentnew);

            //重新查询，得到id
            Payment paymentcreated =this.paymentService.getById(paymentnew);
            registeradd.setPaymentId(paymentcreated.getPaymentId());
            registeradd.setPaymentIfdone(paymentcreated.getIfdone()?1:0);

            if(this.examtimeService.getById(examtimeVo).getRemain()<=0){
                throw new medMISException("添加失败", HttpStatus.BAD_REQUEST);
            }
            //完成挂号后，减少1个余号
            examtimeVo.setRemain(examtimeVo.getRemain()-1);
            this.examtimeService.updateById(examtimeVo);

            this.examregisterService.saveOrUpdate(registeradd);
            return ResultObj.ADD_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("deleteRegister")
    @RequiresRoles("PATIENT")
    public ResultObj deleteRegister(ExamregisterVo examregisterVo) throws medMISException {
        try {
            Examregister registerdel =this.examregisterService.getById( examregisterVo.getExamregisterId());
            //删除缴费单
            this.examregisterService.removeById(registerdel.getPaymentId());
            //恢复余号
            Examtime examtime = this.examtimeService.getById(registerdel.getExamtimeId());
            examtime.setRemain(examtime.getRemain()+1);
            this.examtimeService.updateById(examtime);
            this.examregisterService.removeById(registerdel);


            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping("loadAllRegister")
    public DataGridView loadAllRegister(ExamregisterVo examregisterVo) {
        IPage<Examregister> page = new Page<>(examregisterVo.getPage(), examregisterVo.getLimit());
        QueryWrapper<Examregister> queryWrapper = new QueryWrapper<>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        // 从payment找病人的检查
        QueryWrapper<Payment> paymentqw = new QueryWrapper<>();
        paymentqw.eq("patient_id", user.getUserId());
        List<Payment> payments = this.paymentService.list(paymentqw);
        List<Integer> paymentIds = new ArrayList();
            if (payments.isEmpty()){
                return new DataGridView(page.getTotal(), page.getRecords());
            } else {
                for(Payment payment:payments){
                    paymentIds.add(payment.getPaymentId());
                }
            }
        queryWrapper.in("payment_id", paymentIds);
        // 输入给定查询条件，默认无
        queryWrapper.like((examregisterVo.getPaymentId()!=null), "payment_id", examregisterVo.getPaymentId());
        queryWrapper.ge(examregisterVo.getStartTime() != null, "createtime", examregisterVo.getStartTime());
        queryWrapper.le(examregisterVo.getEndTime() != null, "createtime", examregisterVo.getEndTime());
        queryWrapper.orderByDesc("createtime"); // 排序依据

        this.examregisterService.page(page, queryWrapper);
        for(Examregister examregister:page.getRecords()){
            Exam exam = examService.getById(examregister.getExamId());
            examregister.setExamName(exam.getExamName());
            examregister.setPatientName(user.getName());
            Payment payment =paymentService.getById(examregister.getPaymentId());
            examregister.setPaymentIfdone(payment.getIfdone()?1:0);
            this.examregisterService.updateById(examregister);
        }
        System.out.println(page.getRecords());
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}


