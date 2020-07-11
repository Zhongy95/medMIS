package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
import com.group7.bus.vo.*;
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

import java.util.*;

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
    @Autowired private MeddocService meddocService;

    @RequestMapping("loadAllMedqueuePatient")
    public DataGridView loadAllMedqueuePatient(MedqueueVo medqueueVo) {
        IPage<Medqueue> page = new Page<>(medqueueVo.getPage(), medqueueVo.getLimit());

        IPage<Medqueue> resultPage = new Page<>(medqueueVo.getPage(), medqueueVo.getLimit());
        List<Medqueue> list = new ArrayList<Medqueue>();
        User patient = (User) WebUtils.getSession().getAttribute("user");

            QueryWrapper<Medqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("situation",QUEUE_AFTERRECORD);
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.medqueueService.page(page, queryWrapper);
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Medqueue medqueue:page.getRecords()){
                if(medqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
                Record recordNow = this.recordService.getById( medqueue.getRecordId());
                User targetPatient = this.userService.getById(recordNow.getPatientId());
                if(patient.getUserId().equals(targetPatient.getUserId()))
                {
                    initialMedicineRecord(recordNow);
                    medqueue.setPatientId(patient.getUserId());
                    QueryWrapper<Medtodo> medtodoQueryWrapper =new QueryWrapper<>();
                    medtodoQueryWrapper.eq("record_id",recordNow.getRecordId());
                    List<Medtodo> medtodoList = this.medtodoService.list(medtodoQueryWrapper);
                    float price = 0;
                    for(Medtodo medtodo:medtodoList)
                    {
                        Medicine medicine = this.medicineService.getById(medtodo.getMedId());
                        price = price+medicine.getPrice();
                    }
                    medqueue.setPrice(price);
                    medqueue.setMedName(recordNow.getMedContent());
                }
                queuenumbernow++;
                medqueue.setQueueNumber(queuenumbernow);
                medqueue.setPatientName(patient.getName());
                list.add(medqueue);
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

        QueryWrapper<Medqueue> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("situation",QUEUE_AFTERRECORD);
        queryWrapper.orderByAsc("create_time");// 排序依据
        this.medqueueService.page(page, queryWrapper);
        Integer queuenumbernow = 0; //设置队列排序初始值为0
        for(Medqueue medqueue:page.getRecords()){
            if(medqueue.getSituation().equals(QUEUE_AFTERRECORD)){continue;}
            Record recordNow = this.recordService.getById( medqueue.getRecordId());
            User targetPatient = this.userService.getById(recordNow.getPatientId());
            medqueue.setPatientId(targetPatient.getUserId());
            QueryWrapper<Medtodo> medtodoQueryWrapper =new QueryWrapper<>();
            medtodoQueryWrapper.eq("record_id",recordNow.getRecordId());
            List<Medtodo> medtodoList = this.medtodoService.list(medtodoQueryWrapper);
            float price = 0;
            initialMedicineRecord(recordNow);
            for(Medtodo medtodo:medtodoList)
            {
                Medicine medicine = this.medicineService.getById(medtodo.getMedId());
                price = price+medicine.getPrice();
            }
            medqueue.setPrice(price);
            medqueue.setMedName(recordNow.getMedContent());
            queuenumbernow++;
            medqueue.setQueueNumber(queuenumbernow);
            medqueue.setPatientName(targetPatient.getName());
            list.add(medqueue);
        }

        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }


    @RequestMapping("addMedqueue")
    @RequiresRoles("PATIENT")
    public ResultObj addMedqueue(RecordVo recordVo) throws medMISException {
        try {

            Record recordin = this.recordService.getById(recordVo);
            initialMedicineRecord(recordin);
            if (!recordin.getMedAvailable())
                throw new medMISException("添加失败", HttpStatus.FORBIDDEN);
            if (!recordin.getMedPayIfdone())
                throw new medMISException("添加失败", HttpStatus.BAD_REQUEST);
            Medqueue medqueue =new Medqueue();
            medqueue.setRecordId(recordin.getRecordId());
            if(recordVo.getIfdelivery()!=null)
            {
                if(recordVo.getIfdelivery()&&recordVo.getDeliveryaddr()!=null)
                {
                    medqueue.setIfdelivery(true);
                    medqueue.setDeliveryaddr(recordVo.getDeliveryaddr());
                }
            }
            this.medqueueService.save(medqueue);
            //完成后，将所有medtodo可用状态改为否
            QueryWrapper<Medtodo> medtodoQueryWrapper =new QueryWrapper<>();
            medtodoQueryWrapper.eq("record_id",recordin.getRecordId());
            List<Medtodo> medtodoList = this.medtodoService.list(medtodoQueryWrapper);
            for(Medtodo medtodo :medtodoList)
            {
                medtodo.setAvailable(false);
            }
            this.medtodoService.updateBatchById(medtodoList);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }

    private Record initialMedicineRecord(Record record){
        if(record.getIfdrug())
        {
            QueryWrapper<Medtodo> medtodoQueryWrapper =new QueryWrapper<>();
            medtodoQueryWrapper.eq("record_id",record.getRecordId());
            List<Medtodo> medtodoList = this.medtodoService.list(medtodoQueryWrapper);
            String medName = null;
            HashMap<Integer,Integer> recordMedMap=new HashMap<Integer, Integer>();
            boolean payIfdone = true;
            for(Medtodo medtodo:medtodoList){
                if(!recordMedMap.containsKey(medtodo.getMedId()))
                {
                    //如果单里没有此类药物，则放入，初始值为1
                    recordMedMap.put(medtodo.getMedId(),1);
                }
                else {
                    //如果单里已有此类药物，则+1
                    recordMedMap.put(medtodo.getMedId(),recordMedMap.get(medtodo.getMedId())+1);
                }
                //如果有一个未完成支付，则全单未完成支付
                if(!medtodo.getPayIfdone())
                    payIfdone =false;
            }
            record.setMedPayIfdone(payIfdone);
            record.setMedAvailable(medtodoList.get(0).getAvailable());
            String recordMedContent ="" ;
            for(Map.Entry<Integer, Integer> set :recordMedMap.entrySet()){
                Medicine medicine = this.medicineService.getById(set.getKey());
                if(medicine!=null)
                    recordMedContent=recordMedContent+" "+medicine.getMedName()+set.getValue()+"份";
            }
            record.setMedContent(recordMedContent);
        }
        return record;
    }

    @RequestMapping("MedQueue")
    @RequiresRoles("PHARMACIST")
    public ResultObj MedQueue (MedqueueVo medqueueVo) throws medMISException {
        try {
            Medqueue medqueue = this.medqueueService.getById(medqueueVo.getQueueId());
            if(medqueue.getAvailable()==false)
                throw new medMISException("失效，无法添加", HttpStatus.FORBIDDEN);
            if(!medqueue.getSituation().equals(QUEUE_INQUEUE))
                throw new medMISException("不在队首", HttpStatus.BAD_REQUEST);
            medqueue.setSituation(QUEUE_INRECORD);
            this.medqueueService.saveOrUpdate(medqueue);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("MedQueueFirst")
    @RequiresRoles("PHARMACIST")
    public ResultObj MedQueueFirst () throws medMISException {
        try {
            QueryWrapper<Medqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            queryWrapper.eq("situation",QUEUE_INQUEUE);
            List<Medqueue> medqueueList = this.medqueueService.list(queryWrapper);
            if(medqueueList.size()==0)
                return ResultObj.UPDATE_ERROR;
            Medqueue medqueue = medqueueList.get(0);
            if(!medqueue.getAvailable())
                throw new medMISException("失效，无法添加", HttpStatus.FORBIDDEN);
            if(!medqueue.getSituation().equals(QUEUE_INQUEUE))
                throw new medMISException("不在队首", HttpStatus.BAD_REQUEST);
            medqueue.setSituation(QUEUE_INRECORD);
            this.medqueueService.saveOrUpdate(medqueue);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("medTaken")
    @RequiresRoles("PHARMACIST")
    public ResultObj medTaken (MedqueueVo medqueueVo) throws medMISException {
        try {
            Medqueue medqueue = this.medqueueService.getById(medqueueVo.getQueueId());
            if(!medqueue.getAvailable())
                throw new medMISException("失效，无法添加", HttpStatus.FORBIDDEN);
            if(!medqueue.getSituation().equals(QUEUE_INRECORD))
                throw new medMISException("不在队首", HttpStatus.BAD_REQUEST);
            medqueue.setSituation(QUEUE_AFTERRECORD);
            medqueue.setAvailable(false);
            this.medqueueService.saveOrUpdate(medqueue);
            //创建取药报告
            Meddoc meddoc =new Meddoc();
            User phar = (User) WebUtils.getSession().getAttribute("user");
            meddoc.setPharmacistId(phar.getUserId());
            meddoc.setRecordId(medqueue.getRecordId());
            meddoc.setCreatetime(new Date());
            meddoc.setIfdone(true);
            if(medqueue.getIfdelivery())
            {
                meddoc.setIfdelivery(medqueue.getIfdelivery());
                meddoc.setDeliveryaddr(medqueue.getDeliveryaddr());
            }
            this.meddocService.save(meddoc);



            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }

    }

}

