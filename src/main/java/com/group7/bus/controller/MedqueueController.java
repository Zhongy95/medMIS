package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
import com.group7.bus.vo.ExamqueueVo;
import com.group7.bus.vo.TreatqueueVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.DeptService;
import com.group7.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group7.bus.vo.MedqueueVo;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.group7.sys.common.Constast.*;

/**
 * <p>
 * 排队取药 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-27
 */
@RestController
@RequestMapping("/api/bus/medqueue")
public class MedqueueController {
    @Autowired
    private DeptService deptService;
    @Autowired private RegisterqueueService registerqueueService;
    @Autowired private RegisterService registerService;
    @Autowired private UserService userService;
    @Autowired private PaymentService paymentService;
    @Autowired private MedqueueService medqueueService;
    @Autowired private MedicineService medicineService;
    @Autowired private MedtodoService medtodoService;
    @Autowired private ExamtodoService examtodoService;
    @Autowired private ExamdocService examdocService;
    @Autowired private RecordService recordService;

    @RequestMapping("loadAllMedqueuePatient")
    public DataGridView loadAllMedqueuePatient(MedqueueVo medqueueVo) {
        IPage<Medqueue> page = new Page<>(medqueueVo.getPage(), medqueueVo.getLimit());

        IPage<Medqueue> resultPage = new Page<>(medqueueVo.getPage(), medqueueVo.getLimit());
        List<Medqueue> list = new ArrayList<Medqueue>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        //每个种类的检查都查询一遍
        List<Medicine> medicineList = this.medicineService.list();
        for(Medicine targetmedcine :medicineList){
            QueryWrapper<Medqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.medqueueService.page(page, queryWrapper);
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Medqueue medqueue:page.getRecords()){
                if(medqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
                Medtodo medtodo = medtodoService.getById(medqueue.getMedtodoId());
                Medicine medicine = medicineService.getById(medtodo.getMedId());
                if (medicine.getMedId()!=targetmedcine.getMedId()){continue;}//如果不属于该类，直接进下一个循环
                Payment payment = paymentService.getById(medtodo.getPaymentId());
                medqueue.setPatientId(payment.getPatientId());
                medqueue.setMedName(medicine.getMedName());
                medqueue.setPrice(medicine.getPrice());
                medqueue.setRegisterId(medtodo.getRegisterId());
                medqueue.setRecordId(medtodo.getRecordId());
                queuenumbernow++;
                medqueue.setQueueNumber(queuenumbernow);
                if(!medqueue.getSituation().equals(QUEUE_AFTERRECORD) && user.getUserId().equals(medqueue.getPatientId())){
                    //如果不是目标病人的，就查询不到
                    User userPatient = userService.getById(medqueue.getPatientId());
                    medqueue.setPatientName(userPatient.getName());
                    list.add(medqueue);
                }
            }
        }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }

    @RequestMapping("loadAllMedqueueDoctor")
    public DataGridView loadAllMedqueueDoctor(MedqueueVo medqueueVo) {
        IPage<Medqueue> page = new Page<>(medqueueVo.getPage(), medqueueVo.getLimit());

        IPage<Medqueue> resultPage = new Page<>(medqueueVo.getPage(), medqueueVo.getLimit());
        List<Medqueue> list = new ArrayList<Medqueue>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        //每个种类的检查都查询一遍
        List<Medicine> medicineList = this.medicineService.list();
        for(Medicine targetmedcine :medicineList){
            QueryWrapper<Medqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.medqueueService.page(page, queryWrapper);
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Medqueue medqueue:page.getRecords()){
                if(medqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
                Medtodo medtodo = medtodoService.getById(medqueue.getMedtodoId());
                Medicine medicine = medicineService.getById(medtodo.getMedId());
                if (medicine.getMedId()!=targetmedcine.getMedId()){continue;}//如果不属于该类，直接进下一个循环
                Payment payment = paymentService.getById(medtodo.getPaymentId());
                medqueue.setPatientId(payment.getPatientId());
                medqueue.setMedName(medicine.getMedName());
                medqueue.setPrice(medicine.getPrice());
                medqueue.setRegisterId(medtodo.getRegisterId());
                medqueue.setRecordId(medtodo.getRecordId());
                queuenumbernow++;
                medqueue.setQueueNumber(queuenumbernow);
                medqueue.setPatientName((userService.getById(medqueue.getPatientId())).getName());
                list.add(medqueue);
                }
            }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }
    @RequestMapping("MedRegisterQueue")
    @RequiresRoles("PHARMACIST")
    public ResultObj MedRegisterQueue (MedqueueVo medqueueVo) throws medMISException {
        try {
            Medqueue medqueue = this.medqueueService.getById(medqueueVo.getQueueId());
            if(medqueue.getAvailable()==0)
                throw new medMISException("失效，无法添加", HttpStatus.FORBIDDEN);
            if(!medqueue.getSituation().equals(QUEUE_INQUEUE))
                throw new medMISException("不在队首", HttpStatus.BAD_REQUEST);
            medqueue.setSituation(QUEUE_INRECORD);
            this.medqueueService.saveOrUpdate(medqueue);

//            //检测是否有待办的检查报告
//            User nurse = (User) WebUtils.getSession().getAttribute("user");
//            QueryWrapper<Treatdoc> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("nurse_id",nurse.getUserId());
//            queryWrapper.eq("ifdone",false);
//            List<Treatdoc> treatdocList =this.treatdocService.list(queryWrapper);
//            if(treatdocList.size()!=0)
//                throw new medMISException("还有待检查项目未完成，无法检查新项目", HttpStatus.CONFLICT);
//            //新建治疗报告，ifdone设置为未完成
//            Treatdoc treatdoc =new Treatdoc();
//            treatdoc.setPatientId(treatqueue.getPatientId());
//            treatdoc.setNurseId(nurse.getUserId());
//            Treattodo treattodo= this.treattodoService.getById( treatqueue.getTreattodoId());
//            treatdoc.setTreattodoId(treattodo.getTreattodoId());
//            treatdoc.setRecordId(treattodo.getRecordId());
//            treatdoc.setCreatetime(new Date());
//            treatdoc.setIfdone(false);
//            this.treatdocService.save(treatdoc);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }

    }

}

