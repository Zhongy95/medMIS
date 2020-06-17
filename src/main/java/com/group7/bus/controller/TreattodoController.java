package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Treatment;
import com.group7.bus.entity.Treattodo;
import com.group7.bus.service.TreatmentService;
import com.group7.bus.service.TreattodoService;
import com.group7.bus.vo.TreattodoVo;
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
 * 医生给患者开具的待检查项目单 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/api/bus/treattodo")
@RequiresRoles(value={"PATIENT","DOCTOR"}, logical = Logical.OR)
public class TreattodoController {
    @Autowired
    private TreatmentService treatmentService;

    @Autowired private TreattodoService treattodoService;

    /**
     * 查询
     *
     * @param treattodoVo
     * @return
     */
    @RequestMapping("loadAllTreattodo")
    public DataGridView loadAllTreattodo(TreattodoVo treattodoVo) {
        User user = (User) WebUtils.getSession().getAttribute("user");
        IPage<Treattodo> page = treattodoService.getTreattodoByPatientId(
                new Page<>(treattodoVo.getPage(), treattodoVo.getLimit()), user.getUserId());

        for(Treattodo treattodo : page.getRecords()) {
            Treatment treat = this.treatmentService.getById(treattodo.getTreatmentId());
            treattodo.setTreatName(treat.getTreatmentName());
            treattodo.setPatientName(user.getName());
        }

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     *
     * @param treattodoVo
     * @return
     */
    @RequestMapping("deleteTreattodo")
    public ResultObj deleteTreattodo(TreattodoVo treattodoVo) throws medMISException {
        try {
            treattodoService.removeById(treattodoVo.getTreattodoId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 批量删除
     *
     * @param treattodoVo
     * @return
     */
    @RequestMapping("batchDeleteTreattodo")
    public ResultObj batchDeleteTreattodo(TreattodoVo treattodoVo) throws medMISException {
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : treattodoVo.getIds()) {
                idList.add(id);
            }
            treattodoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
        }
    }
}



