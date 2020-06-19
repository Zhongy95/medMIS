package com.group7.bus.controller;


import cn.hutool.db.AbstractDb;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.mapper.RegisterqueueMapper;
import com.group7.bus.service.DoctortimeService;
import com.group7.bus.service.PaymentService;
import com.group7.bus.service.RegisterService;
import com.group7.bus.service.RegisterqueueService;
import com.group7.bus.vo.DoctortimeVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group7.bus.vo.RegisterVo;
import com.group7.bus.entity.Register;

import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import static com.group7.sys.common.Constast.*;

/**
 * <p>
 * 排队看病 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/api/bus/registerqueue")
public class RegisterqueueController {
    @Autowired private DeptService deptService;
    @Autowired private RegisterqueueService registerqueueService;
    @Autowired private RegisterService registerService;
    @Autowired private UserService userService;
    @Autowired(required = false) private RegisterqueueMapper registerqueueMapper;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping("loadAllRegisterqueuePatient")
    public DataGridView loadAllRegisterqueuePatient(RegisterqueueVo registerqueueVo) {
        IPage<Registerqueue> page = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
        QueryWrapper<Registerqueue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");// 排序依据
        this.registerqueueService.page(page, queryWrapper);
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Registerqueue> resultPage = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
        List<Registerqueue> list = new ArrayList<Registerqueue>();
        Integer targetDoctorid =null;//设置要看的目标医生
        for(Registerqueue registerqueue:page.getRecords()){
            Register register = this.registerService.getById(registerqueue.getRegisterId());
            if(user.getUserId().equals(register.getPatientId()))
            {
                if(targetDoctorid==null) {
                    targetDoctorid = register.getDoctorId();
                    break;
                }

            }
        }
        Integer queuenumbernow = 0; //设置队列排序初始值为0
        for(Registerqueue registerqueue:page.getRecords()){
            Register register = registerService.getById(registerqueue.getRegisterId());
            registerqueue.setPatientId(register.getPatientId());
            registerqueue.setDoctorId(register.getDoctorId());
            if(registerqueue.getDoctorId().equals(targetDoctorid)&& !registerqueue.getSituation().equals(QUEUE_AFTERRECORD)){
                User userPatient = userService.getById(registerqueue.getPatientId());
                registerqueue.setPatientName(userPatient.getName());
                User userDoctor = userService.getById(registerqueue.getDoctorId());
                registerqueue.setDeptId(Integer.parseInt(userDoctor.getDeptId()));
                Dept dept = deptService.getById(userDoctor.getDeptId());
                registerqueue.setDeptName((dept.getDeptName()));
                registerqueue.setDoctorName(userDoctor.getName());
                queuenumbernow++;
                registerqueue.setQueueNum(queuenumbernow);
                list.add(registerqueue);
            }
        }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }

    @RequestMapping("loadAllRegisterqueueDoctor")
    public DataGridView loadAllRegisterqueueDoctor(RegisterqueueVo registerqueueVo) {
        IPage<Registerqueue> page = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
        QueryWrapper<Registerqueue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");// 排序依据
        this.registerqueueService.page(page, queryWrapper);
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Registerqueue> resultPage = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
        List<Registerqueue> list = new ArrayList<Registerqueue>();
        Integer queuenumbernow = 0; //设置队列排序初始值为0
        for(Registerqueue registerqueue:page.getRecords()){
            Register register = registerService.getById(registerqueue.getRegisterId());
            registerqueue.setPatientId(register.getPatientId());
            registerqueue.setDoctorId(register.getDoctorId());
            if(user.getUserId().equals(registerqueue.getDoctorId())&& !registerqueue.getSituation().equals(QUEUE_AFTERRECORD))
            {
                User userPatient = userService.getById(registerqueue.getPatientId());
                registerqueue.setPatientName(userPatient.getName());
                User userDoctor = userService.getById(registerqueue.getDoctorId());
                registerqueue.setDeptId(Integer.parseInt(userDoctor.getDeptId()));
                Dept dept = deptService.getById(userDoctor.getDeptId());
                registerqueue.setDeptName((dept.getDeptName()));
                registerqueue.setDoctorName(userDoctor.getName());
                list.add(registerqueue);
                queuenumbernow++;
                registerqueue.setQueueNum(queuenumbernow);
            }
        }
        resultPage.setRecords(list);
        resultPage.setTotal(page.getTotal());
        resultPage.setPages(page.getPages());
        return new DataGridView(resultPage.getTotal(), resultPage.getRecords());
    }

    @RequestMapping("popRegisterQueue")
    public ResultObj popRegisterQueue(RegisterqueueVo registerqueueVo) throws medMISException {
        try {
            User user = (User) WebUtils.getSession().getAttribute("user");

            String deleteStatement = "delete bus_registerqueue from (bus_registerqueue INNER JOIN bus_register on bus_registerqueue.register_id=bus_register.register_id) where queue_number=1 and bus_register.doctor_id="+user.getUserId();
            int delete = registerqueueMapper.delete(deleteStatement);
            //删除

            String updateStatement = "UPDATE bus_registerqueue,bus_register set queue_number=queue_number-1 where bus_registerqueue.register_id=bus_register.register_id and bus_register.doctor_id="+user.getUserId();
            int update = registerqueueMapper.update(updateStatement);
            return ResultObj.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("呼叫失败",HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("recordRegisterQueue")
    public ResultObj recordRegisterQueue(RegisterqueueVo registerqueueVo) throws medMISException {
        try {
            User user = (User) WebUtils.getSession().getAttribute("user");
            IPage<Registerqueue> page = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
            QueryWrapper<Registerqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            this.registerqueueService.page(page, queryWrapper);
            IPage<Registerqueue> resultPage = new Page<>(registerqueueVo.getPage(), registerqueueVo.getLimit());
            List<Registerqueue> list = new ArrayList<Registerqueue>();
            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Registerqueue registerqueue:page.getRecords()){
                Register register = registerService.getById(registerqueue.getRegisterId());
                registerqueue.setPatientId(register.getPatientId());
                registerqueue.setDoctorId(register.getDoctorId());
                if(user.getUserId().equals(registerqueue.getDoctorId()))//只有医生确认后，才开始操作
                {
                    if(registerqueue.getSituation().equals(QUEUE_INQUEUE))
                        registerqueue.setSituation(QUEUE_INRECORD);
                    this.registerqueueService.updateById(registerqueue);
                    break;
                }
            }

            return ResultObj.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("呼叫失败",HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping("addRegisterqueue")
    public ResultObj addRegisterqueue(RegisterVo registerVo) throws medMISException {
        try {
            if(!registerVo.getAvailable())
                throw new medMISException("添加失败", HttpStatus.FORBIDDEN);
            if(!registerVo.getPaymentIfdone())
                throw new medMISException("添加失败", HttpStatus.BAD_REQUEST);
            Register registerin = this.registerService.getById(registerVo);
            Registerqueue registerqueue = new Registerqueue();

            registerqueue.setRegisterId(registerin.getRegisterId());
            IPage<Registerqueue> page = new Page<>();
            QueryWrapper<Registerqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            //queryWrapper.last("limit 1");
            this.registerqueueService.page(page, queryWrapper);//获得排最后的一条记录

            this.registerqueueService.save(registerqueue);
            registerin.setAvailable(false);//完成后，将可用状态改为否
            this.registerService.updateById(registerin);
            return ResultObj.ADD_SUCCESS;
        }catch(Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }


    }














    @RequestMapping("loadRegisterToRecord")
    public Registerqueue loadRegisterToRecord() throws medMISException {

        try {
            Registerqueue registerqueuetoload = new Registerqueue();

            QueryWrapper<Registerqueue> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("create_time");// 排序依据
            List<Registerqueue> list = new ArrayList<Registerqueue>();
            list=this.registerqueueService.list(queryWrapper);
            User user = (User) WebUtils.getSession().getAttribute("user");

            Integer queuenumbernow = 0; //设置队列排序初始值为0
            for(Registerqueue registerqueue:list){
                Register register = registerService.getById(registerqueue.getRegisterId());
                registerqueue.setPatientId(register.getPatientId());
                registerqueue.setDoctorId(register.getDoctorId());
                if(user.getUserId().equals(registerqueue.getDoctorId())&& registerqueue.getSituation().equals(QUEUE_INRECORD))
                {
                    User userPatient = userService.getById(registerqueue.getPatientId());
                    registerqueue.setPatientName(userPatient.getName());
                    User userDoctor = userService.getById(registerqueue.getDoctorId());
                    registerqueue.setDeptId(Integer.parseInt(userDoctor.getDeptId()));
                    Dept dept = deptService.getById(userDoctor.getDeptId());
                    registerqueue.setDeptName((dept.getDeptName()));
                    registerqueue.setDoctorName(userDoctor.getName());
                    registerqueuetoload=registerqueue;
                }
            }

            return registerqueuetoload;


        }catch(Exception e) {
            e.printStackTrace();
            throw new medMISException("获取失败", HttpStatus.UNAUTHORIZED);
        }


    }




//    //删除
//    @RequestMapping("deleteRegisterqueue")
//    public ResultObj deleteRegisterqueue(RegisterqueueVo registerqueueVo) throws medMISException {
//        try {
//            Registerqueue registerqueuedel =this.registerqueueService.getById(registerqueueVo.getQueueId());
//            //删除
//
//            this.doctortimeService.updateById(doctortime);
//            this.registerService.removeById(registerdel);
//
//
//            return ResultObj.DELETE_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
//        }
//    }



}

