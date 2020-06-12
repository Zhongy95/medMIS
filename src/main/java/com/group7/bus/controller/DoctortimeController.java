package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Doctortime;
import com.group7.bus.service.DoctortimeService;
import com.group7.bus.service.impl.DoctortimeServiceImpl;
import com.group7.bus.vo.DoctortimeVo;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.TreeNode;
import com.group7.sys.controller.DeptController;
import com.group7.sys.entity.Dept;
import com.group7.sys.service.DeptService;
import com.group7.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医生工作时间 前端控制器
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/api/doctortime")
@RequiresRoles("PATIENT")
public class DoctortimeController {

    private final DoctortimeServiceImpl doctortimeServiceImpl;
    private DeptService deptService;

    @Autowired
    public DoctortimeController(DoctortimeServiceImpl doctortimeServiceImpl, DeptService deptService) {
        this.doctortimeServiceImpl = doctortimeServiceImpl;
        this.deptService = deptService;
    }

    @RequestMapping("loadDoctortimeManagerLeftTreeJson")
    public DataGridView loadDoctortimeManagerLeftTreeJson() {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 2).or().eq("dept_id", 2);
        List<Dept> list = deptService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Dept dept : list) {
            // 如果以后有第二层门诊部门，这里要重新设计pid
            if (dept.getDeptId() == 2)
                treeNodes.add(new TreeNode(dept.getDeptId(), 0, dept.getDeptName(), true));
            else
                treeNodes.add(new TreeNode(dept.getDeptId(), 2, dept.getDeptName(), false));
                // 2是门诊
        }
        return new DataGridView(treeNodes);
    }

    @RequestMapping("loadAllDoctortime")
    public DataGridView loadAllDoctortime(DoctortimeVo doctortimeVo) {
        IPage<DoctortimeVo> page = new Page<>(doctortimeVo.getPage(), doctortimeVo.getLimit());
        Map<String,Object> m = new HashMap<>();
        Integer deptId = doctortimeVo.getDeptId();
        if (deptId != null && deptId != 2)
            m.put("deptId", deptId);
        // pid留着以后扩展用
        m.put("pid", doctortimeVo.getPid());
        m.put("doctorName", doctortimeVo.getDoctorName());
        IPage<DoctortimeVo> truePage = doctortimeServiceImpl.loadAllDoctortime(m, page);
        return new DataGridView(truePage.getTotal(), truePage.getRecords());
    }
}

