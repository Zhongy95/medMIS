package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.DataGridView;
import com.group7.bus.entity.Register;
import com.group7.bus.service.RegisterService;
import com.group7.bus.vo.RegisterVo;
import com.group7.sys.common.ResultObj;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;

import com.group7.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
        queryWrapper.like( registerVo.getPatientId()!=null,"patient_id", registerVo.getPatientId());
//        queryWrapper.like(
//                StringUtils.isNotBlank(registerVo.getOperName()), "oper_name", registerVo.getOperName());
//        queryWrapper.ge(registerVo.getStartTime() != null, "create_time", registerVo.getStartTime());
//        queryWrapper.le(registerVo.getEndTime() != null, "create_time", registerVo.getEndTime());
//        queryWrapper.orderByDesc("create_time"); // 排序依据

        this.registerService.page(page, queryWrapper);
        for(Register register:page.getRecords()){
            User user = userService.getById(register.getDoctorId());
            register.setDoctorName(user.getName());
        }
        System.out.println(page.getRecords());
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    
    @RequestMapping("payRegister")
    public ResultObj payRegister(RegisterVo registerVo) throws medMISException {
        try {
            registerVo.setPaymentIfdone(true);
            this.registerService.updateById(registerVo);
            return ResultObj.UPDATE_SUCCESS;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("更新失败", HttpStatus.UNAUTHORIZED);
        }
    }
    
    

}

