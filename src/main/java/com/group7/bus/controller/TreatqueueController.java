package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.entity.Record;
import com.group7.bus.mapper.TreatqueueMapper;
import com.group7.bus.service.*;
import com.group7.bus.vo.TreatqueueVo;

import com.group7.bus.vo.TreatqueueVo;
import com.group7.bus.vo.TreattodoVo;
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

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.group7.sys.common.Constast.*;

/**
 * <p>
 * 排队治疗 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-24
 */
@RestController
@RequestMapping("/api/bus/treatqueue")
public class TreatqueueController {
    @Autowired
    private DeptService deptService;
    @Autowired private RegisterqueueService registerqueueService;
    @Autowired private RegisterService registerService;
    @Autowired private UserService userService;
    @Autowired private PaymentService paymentService;
    @Autowired private TreatmentService treatmentService;
    @Autowired private TreatqueueService treatqueueService;
    @Autowired private TreattodoService treattodoService;
    @Autowired private RecordService recordService;
    @Autowired private TreatdocService treatdocService;

    @RequestMapping("loadAllTreatqueuePatient")
    public DataGridView loadAllTreatqueuePatient(TreatqueueVo treatqueueVo) {
        IPage<Treatqueue> page = new Page<>(treatqueueVo.getPage(), treatqueueVo.getLimit());

        IPage<Treatqueue> resultPage = new Page<>(treatqueueVo.getPage(), treatqueueVo.getLimit());
        List<Treatqueue> list = new ArrayList<Treatqueue>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        //每个种类的检查都查询一遍
        List<Treatment> treatmentList = this.treatmentService.list();
        for(Treatment targetTreatment :treatmentList){
            QueryWrapper<Treatqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.treatqueueService.page(page, queryWrapper);
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Treatqueue treatqueue:page.getRecords()){
                if(treatqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
                if(!treatqueue.getAvailable()){continue;}
                Treattodo treattodo = treattodoService.getById(treatqueue.getTreattodoId());
                Treatment treatment = treatmentService.getById(treattodo.getTreatmentId());
                Record record = recordService.getById(treattodo.getRecordId());
                Register register = registerService.getById(treattodo.getRegisterId());
                User userpatient = userService.getById(register.getPatientId());

                if (treatment.getTreatmentId()!=targetTreatment.getTreatmentId()){continue;}//如果不属于该类，直接进下一个循环
                treatqueue.setPatientId(userpatient.getUserId());
                treatqueue.setTreatmentName(treatment.getTreatmentName());
                treatqueue.setPrice(treatment.getPrice());
                treatqueue.setPatientName(userpatient.getName());
                treatqueue.setRecordId((treattodo.getRecordId()));
                treatqueue.setRegisterId(treattodo.getRegisterId());
                queuenumbernow++;
                treatqueue.setQueueNumber(queuenumbernow);
                if(treatqueue.getAvailable() && user.getUserId().equals(treatqueue.getPatientId())){
                    //如果不是目标病人的，就查询不到
                    list.add(treatqueue);
                }
            }
        }

        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }
//
@RequestMapping("loadAllTreatqueueDoctor")
public DataGridView loadAllTreatqueueDoctor(TreatqueueVo treatqueueVo) {
    IPage<Treatqueue> page = new Page<>(treatqueueVo.getPage(), treatqueueVo.getLimit());

    IPage<Treatqueue> resultPage = new Page<>(treatqueueVo.getPage(), treatqueueVo.getLimit());
    List<Treatqueue> list = new ArrayList<Treatqueue>();
    User user = (User) WebUtils.getSession().getAttribute("user");
    //每个种类的检查都查询一遍
    List<Treatment> treatmentList = this.treatmentService.list();
    for(Treatment targetTreatment :treatmentList){
        QueryWrapper<Treatqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
        this.treatqueueService.page(page, queryWrapper);
        Integer queuenumbernow = 0; //设置队列排序初始值为0
        for(Treatqueue treatqueue:page.getRecords()){
            if(treatqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
            if(!treatqueue.getAvailable()){continue;}
            Treattodo treattodo = treattodoService.getById(treatqueue.getTreattodoId());
            Treatment treatment = treatmentService.getById(treattodo.getTreatmentId());
            Record record = recordService.getById(treattodo.getRecordId());
            Register register = registerService.getById(treattodo.getRegisterId());
            User userpatient = userService.getById(register.getPatientId());

            if (!treatment.getTreatmentId().equals(targetTreatment.getTreatmentId())){continue;}//如果不属于该类，直接进下一个循环
            treatqueue.setPatientId(userpatient.getUserId());
            treatqueue.setTreatmentName(treatment.getTreatmentName());
            treatqueue.setPrice(treatment.getPrice());
            treatqueue.setPatientName(userpatient.getName());
            treatqueue.setRecordId((treattodo.getRecordId()));
            treatqueue.setRegisterId(treattodo.getRegisterId());
            queuenumbernow++;
            treatqueue.setQueueNumber(queuenumbernow);
            list.add(treatqueue);
        }
    }
    resultPage.setRecords(list);
    resultPage.setTotal(page.getTotal());
    resultPage.setPages(page.getPages());
    return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }


    @RequestMapping("addTreatqueue")
    public ResultObj addTreatqueue(TreattodoVo treattodoVo) throws medMISException {
        try {
            Treattodo treattodoin = this.treattodoService.getById(treattodoVo);
            if (!treattodoin.getAvailable())
                throw new medMISException("添加失败", HttpStatus.FORBIDDEN);
            if (!treattodoin.getPayIfdone())
                throw new medMISException("添加失败", HttpStatus.BAD_REQUEST);
            Treatqueue treatqueue =new Treatqueue();
            treatqueue.setTreattodoId(treattodoin.getTreattodoId());
            this.treatqueueService.save(treatqueue);
            treattodoin.setAvailable(false);//完成后，将可用状态改为否
            this.treattodoService.updateById(treattodoin);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }




    @RequestMapping("TreatRegisterQueue")
    @RequiresRoles("NURSE")
    public ResultObj TreatRegisterQueue (TreatqueueVo treatqueueVo) throws medMISException {
        try {
            Treatqueue treatqueue = this.treatqueueService.getById(treatqueueVo.getQueueId());
            if(!treatqueue.getAvailable())
                throw new medMISException("失效，无法添加", HttpStatus.FORBIDDEN);
            if(!treatqueue.getSituation().equals(QUEUE_INQUEUE))
                throw new medMISException("不在队首", HttpStatus.BAD_REQUEST);
            treatqueue.setSituation(QUEUE_INRECORD);
            this.treatqueueService.saveOrUpdate(treatqueue);

//            //检测是否有待办的检查报告
            User nurse = (User) WebUtils.getSession().getAttribute("user");
            QueryWrapper<Treatdoc> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("nurse_id",nurse.getUserId());
            queryWrapper.eq("ifdone",false);
            List<Treatdoc> treatdocList =this.treatdocService.list(queryWrapper);
            if(treatdocList.size()!=0)
                throw new medMISException("还有待检查项目未完成，无法检查新项目", HttpStatus.CONFLICT);
            //新建治疗报告，ifdone设置为未完成
            Treatdoc treatdoc =new Treatdoc();
            treatdoc.setPatientId(treatqueue.getPatientId());
            treatdoc.setNurseId(nurse.getUserId());
            Treattodo treattodo= this.treattodoService.getById( treatqueue.getTreattodoId());
            treatdoc.setTreattodoId(treattodo.getTreattodoId());
            treatdoc.setRecordId(treattodo.getRecordId());
            treatdoc.setCreatetime(new Date());
            treatdoc.setIfdone(false);
            this.treatdocService.save(treatdoc);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }

    }
}

