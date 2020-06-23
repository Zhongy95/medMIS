package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.entity.Record;
import com.group7.bus.service.*;
import com.group7.bus.service.impl.RecordServiceImpl;
import com.group7.bus.vo.RecordVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Dept;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.DeptService;
import com.group7.sys.service.UserService;
import com.group7.sys.vo.UserVo;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.group7.sys.common.Constast.QUEUE_AFTERRECORD;
import static com.group7.sys.common.Constast.QUEUE_INRECORD;

/**
 * <p>
 * 病历记录 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-17
 */
@RestController
@RequestMapping("api/bus/record")
public class RecordController {

    private RecordServiceImpl recordServiceImpl;
    private RecordService recordService;
    private UserService userService;
    private DeptService deptService;
    @Autowired private ExamtodoService examtodoService;
    @Autowired private TreattodoService treattodoService;
    @Autowired private MedtodoService medtodoService;
    @Autowired private RegisterqueueService registerqueueService;


    @Autowired
    public RecordController(RecordServiceImpl recordServiceImpl, RecordService recordService, UserService userService, DeptService deptService) {
        this.recordServiceImpl = recordServiceImpl;
        this.recordService = recordService;
        this.userService = userService;
        this.deptService = deptService;
    }

    @RequestMapping("loadAllRecordByPatient")
    @RequiresRoles("PATIENT")
    public DataGridView loadAllRecordByPatient(RecordVo recordVo) {
        IPage<Record> page = new Page<>(recordVo.getPage(), recordVo.getLimit());
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> doctorqw = new QueryWrapper<>();
        List<User> doctors = new ArrayList<>();
        List<Integer> doctorIds = new ArrayList<>();
        User patient = (User) WebUtils.getSession().getAttribute("user");
        if (StringUtils.isNotBlank(recordVo.getDoctorName())) {
            doctorqw.like("user_name", recordVo.getDoctorName());
            doctors = this.userService.list(doctorqw);
            if (doctors.isEmpty()){
                return new DataGridView(page.getTotal(), page.getRecords());
            } else {
                for(User user:doctors){
                    doctorIds.add(user.getUserId());
                }
            }
        }
        queryWrapper.eq("patient_id", patient.getUserId())
                .in(!doctorIds.isEmpty(),"doctor_id", doctorIds)
                .ge(recordVo.getStartTime() != null, "createtime", recordVo.getStartTime())
                .orderByDesc("createtime");
        this.recordService.page(page, queryWrapper);
        for(Record record:page.getRecords()) {
            User doctor = this.userService.getById(record.getDoctorId());
            refactorRecord(doctor, record, patient);
        }
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @RequestMapping("loadAllRecordByDoctor")
    @RequiresRoles("DOCTOR")
    public DataGridView loadAllRecordByDoctor(RecordVo recordVo) {
        IPage<Record> page = new Page<>(recordVo.getPage(), recordVo.getLimit());
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> patientqw = new QueryWrapper<>();
        List<User> patients = new ArrayList<>();
        List<Integer> patientIds = new ArrayList<>();
        User doctor = (User) WebUtils.getSession().getAttribute("user");
        if (StringUtils.isNotBlank(recordVo.getDoctorName())) {
            patientqw.like("user_name", recordVo.getDoctorName());
            patients = this.userService.list(patientqw);
            if (patients.isEmpty()){
                return new DataGridView(page.getTotal(), page.getRecords());
            } else {
                for(User user:patients){
                    patientIds.add(user.getUserId());
                }
            }
        }
        queryWrapper.eq("doctor_id", doctor.getUserId())
                .in(!patientIds.isEmpty(),"patient_id", patientIds)
                .ge(recordVo.getStartTime() != null, "createtime", recordVo.getStartTime())
                .orderByDesc("createtime");
        this.recordService.page(page, queryWrapper);
        for(Record record:page.getRecords()) {
            User patient = this.userService.getById(record.getPatientId());
            refactorRecord(doctor, record, patient);
        }
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    private void refactorRecord(User doctor, Record record, User patient) {
        QueryWrapper<Dept> deptqw = new QueryWrapper<>();
        deptqw.eq("dept_id",doctor.getDeptId());
        Dept dept = this.deptService.getOne(deptqw);
        record.setDeptName(dept.getDeptName());
        record.setDoctorName(doctor.getName());
        record.setPatientName(patient.getName());
    }

    @RequestMapping("addRecord")
    @RequiresRoles("DOCTOR")
    public ResultObj addRecord(RecordVo recordVo) throws medMISException {
        try {
            User doctor = (User) WebUtils.getSession().getAttribute("user");
            recordVo.setCreatetime(new Date());
            recordVo.setDoctorId(doctor.getUserId());
            recordService.save(recordVo);
            Record recordnow = this.recordService.getById(recordVo);
            //更新ExamToDo内容,因为之前没有recordId
            QueryWrapper<Examtodo> queryWrapperExam = new QueryWrapper<>();
            queryWrapperExam.eq("register_id",recordnow.getRegisterId());
            List<Examtodo> examtodoList= this.examtodoService.list(queryWrapperExam);
            if(examtodoList.size()!=0){
                for(Examtodo examtodo:examtodoList){
                    if(examtodo.getRecordId()==null)
                        examtodo.setRecordId(recordnow.getRecordId());
                }
                this.examtodoService.updateBatchById(examtodoList);
                recordnow.setIfexam(true);
                this.recordService.updateById(recordnow);
            }
            //更新TreatToDo内容,因为之前没有recordId
            QueryWrapper<Treattodo> queryWrapperTreat = new QueryWrapper<>();
            queryWrapperTreat.eq("register_id",recordnow.getRegisterId());
            List<Treattodo> treattodoList= this.treattodoService.list(queryWrapperTreat);
            if(treattodoList.size()!=0){
                for(Treattodo treattodo:treattodoList){
                    if(treattodo.getRecordId()==null)
                        treattodo.setRecordId(recordnow.getRecordId());
                }
                this.treattodoService.updateBatchById(treattodoList);
                recordnow.setIftreat(true);
                this.recordService.updateById(recordnow);
            }
            //更新MedToDo内容,因为之前没有recordId
            QueryWrapper<Medtodo> queryWrapperMed = new QueryWrapper<>();
            queryWrapperMed.eq("register_id",recordnow.getRegisterId());
            List<Medtodo> medtodoList= this.medtodoService.list(queryWrapperMed);
            if(medtodoList.size()!=0){
                for(Medtodo medtodo:medtodoList){
                    if(medtodo.getRecordId()==null)
                        medtodo.setRecordId(recordnow.getRecordId());
                }
                this.medtodoService.updateBatchById(medtodoList);
                recordnow.setIfdrug(true);
                this.recordService.updateById(recordnow);
            }

            //将该排队置为不可用
            Registerqueue registerqueuenow = this.registerqueueService.getById(recordVo.getQueueId());
            registerqueuenow.setSituation(QUEUE_AFTERRECORD);
            this.registerqueueService.updateById(registerqueuenow);


            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            throw new medMISException("添加病历失败", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("loadPatient")
    @RequiresRoles("DOCTOR")
    public User loadPatient(UserVo userVo) throws medMISException {
        try {
            return this.userService.getById(userVo);
        } catch (Exception e) {
            throw new medMISException("读取病人信息失败", HttpStatus.BAD_REQUEST);
        }
    }


}

