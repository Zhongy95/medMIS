package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.DataGridView;
import com.group7.bus.entity.Register;
import com.group7.bus.service.RegisterService;
import com.group7.bus.vo.RegisterVo;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Dept;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;

import com.group7.sys.service.DeptService;
import com.group7.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 挂号单 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/api/bus/register")
public class RegisterController {

    @Autowired private RegisterService registerService;

    @Autowired private UserService userService;

    @Autowired private DeptService deptService;


    /**
     * 查询
     *
     * @param registerVo
     * @return
     */
    @RequestMapping("loadAllRegister")
    public DataGridView loadAllRegister(RegisterVo registerVo) {
        IPage<Register> page = new Page<>(registerVo.getPage(), registerVo.getLimit());

        QueryWrapper<Register> queryWrapper = new QueryWrapper<>();
        // 输入给定查询条件，默认无
        queryWrapper.like((registerVo.getRegisterId()!=null), "register_id", registerVo.getRegisterId());
        queryWrapper.like((registerVo.getPatientId()!=null), "patient_id", registerVo.getPatientId());
        queryWrapper.like((registerVo.getDoctorId()!=null), "doctor_id", registerVo.getDoctorId());
        queryWrapper.like((registerVo.getPaymentId()!=null), "payment_id", registerVo.getPaymentId());

        queryWrapper.ge(registerVo.getStartTime() != null, "createtime", registerVo.getStartTime());
        queryWrapper.le(registerVo.getEndTime() != null, "createtime", registerVo.getEndTime());
        queryWrapper.orderByDesc("createtime"); // 排序依据
        this.registerService.page(page, queryWrapper);
        for(Register register:page.getRecords()){
            User userdoc = userService.getById(register.getDoctorId());
            register.setDoctorName(userdoc.getName());
            Dept dept = deptService.getById( userdoc.getDeptId());
            register.setDeptName((dept.getDeptName()));
            User userpat =userService.getById(register.getPatientId());
            register.setPatientName(userpat.getName());
        }
        System.out.println(page.getRecords());
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param registerVo
     * @return
     */
    @RequestMapping("addRegister")
    public ResultObj addRegister(RegisterVo registerVo) throws medMISException {
        try {
            registerVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            registerVo.setRegisterId(registerVo.getRegisterId());
            registerVo.setPatientId(registerVo.getPatientId());
            registerVo.setDoctorId(registerVo.getDoctorId());
            registerVo.setPaymentId(registerVo.getPaymentId());
            registerVo.setPaymentIfdone(registerVo.getPaymentIfdone());
            registerVo.setAvailable(registerVo.getAvailable());

            this.registerService.saveOrUpdate(registerVo);
            return ResultObj.ADD_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping("payRegister")
    public ResultObj payRegister(RegisterVo registerVo) throws medMISException {
        try {
            registerVo.setPaymentIfdone(true);
            this.registerService.updateById(registerVo);
            return ResultObj.UPDATE_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("支付失败", HttpStatus.UNAUTHORIZED);
        }
    }
    /**
     * 删除
     *
     * @param registerVo
     * @return
     */
    @RequestMapping("deleteRegister")
    public ResultObj deleteRegister(RegisterVo registerVo) throws medMISException {
        try {
            registerService.removeById(registerVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 批量删除
     *
     * @param registerVo
     * @return
     */

    @RequestMapping("batchDeleteRegister")
    public ResultObj batchDeleteRegister(RegisterVo registerVo) throws medMISException {
        try {
            Collection<Serializable> registerIdList = new ArrayList<>();

            for (Integer registerId : registerVo.getRegisterIds()) {
                registerIdList.add(registerId);
            }
            System.out.println(registerIdList);
            registerService.removeByIds(registerIdList);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);

        }
    }



}

