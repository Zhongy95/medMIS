package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Medicine;
import com.group7.bus.entity.Medtodo;
import com.group7.bus.service.MedicineService;
import com.group7.bus.service.MedtodoService;
import com.group7.bus.vo.MedtodoVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * 医生给患者开具的药物单 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/api/bus/medtodo")
@RequiresRoles(value={"PATIENT","DOCTOR"}, logical = Logical.OR)
public class MedtodoController {
    @Autowired
    private MedicineService medicineService;

    @Autowired private MedtodoService medtodoService;

    /**
     * 查询
     *
     * @param medtodoVo
     * @return
     */
    @RequestMapping("loadAllMedtodo")
    public DataGridView loadAllMedtodo(MedtodoVo medtodoVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Medtodo> page = medtodoService.getMedtodoByPatientId(
                new Page<>(medtodoVo.getPage(), medtodoVo.getLimit()), user.getUserId());

        for(Medtodo medtodo : page.getRecords()) {
            Medicine med = this.medicineService.getById(medtodo.getMedId());
            medtodo.setMedName(med.getMedName());
            medtodo.setPatientName(user.getName());
        }

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     *
     * @param medtodoVo
     * @return
     */
    @RequestMapping("deleteMedtodo")
    public ResultObj deleteMedtodo(MedtodoVo medtodoVo) throws medMISException {
        try {
            medtodoService.removeById(medtodoVo.getMedtodoId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 批量删除
     *
     * @param medtodoVo
     * @return
     */
    @RequestMapping("batchDeleteMedtodo")
    public ResultObj batchDeleteMedtodo(MedtodoVo medtodoVo) throws medMISException {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : medtodoVo.getIds()) {
                idList.add(id);
            }
            medtodoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }
}



