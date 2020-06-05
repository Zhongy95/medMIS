package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.TreeNode;
import com.group7.sys.entity.Dept;
import com.group7.sys.service.DeptService;
import com.group7.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端控制器
 *
 * @author Robin
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/api/dept/")
public class DeptController {

  @Autowired private DeptService deptService;

  /**
   * 部门页面控制器
   *
   * @return
   */
  @RequestMapping("loadDeptManagerLeftTreeJson")
  public DataGridView loadDeptManagerLeftTreeJson() {
    List<Dept> list = this.deptService.list();
    List<TreeNode> treeNodes = new ArrayList<>();
    for (Dept dept : list) {
      Boolean spread = dept.getOpen();
      treeNodes.add(new TreeNode(dept.getDeptId(), dept.getPid(), dept.getName(), spread));
    }
    return new DataGridView(treeNodes);
  }

  @RequestMapping("loadAllDept")
  public DataGridView loadAllDept(DeptVo deptVo) {
    IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
    QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
    // 输入给定查询条件，默认无
    queryWrapper.like(StringUtils.isNotBlank(deptVo.getName()), "name", deptVo.getName());
    queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()), "address", deptVo.getAddress());
    queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()), "remark", deptVo.getRemark());
    queryWrapper.orderByDesc("ordernum"); // 排序依据
    queryWrapper
        .eq(deptVo.getDeptId() != null, "id", deptVo.getDeptId())
        .or()
        .eq(deptVo.getDeptId() != null, "pid", deptVo.getDeptId());

    this.deptService.page(page, queryWrapper);

    return new DataGridView(page.getTotal(), page.getRecords());
  }
}
