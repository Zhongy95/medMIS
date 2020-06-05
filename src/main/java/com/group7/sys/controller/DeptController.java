package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.TreeNode;
import com.group7.sys.entity.Dept;
import com.group7.sys.service.DeptService;
import com.group7.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        .eq(deptVo.getDeptId() != null, "dept_id", deptVo.getDeptId())
        .or()
        .eq(deptVo.getDeptId() != null, "pid", deptVo.getDeptId());

    this.deptService.page(page, queryWrapper);

    return new DataGridView(page.getTotal(), page.getRecords());
  }
  /**
   * 添加部门
   * @param deptVo
   * @return
   */
  @RequestMapping("addDept")
  public ResultObj addDept(DeptVo deptVo){
    try {
      deptVo.setCreatetime(new Date());
      deptService.save(deptVo);
      return ResultObj.ADD_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.ADD_ERROR;
    }
  }

  /**
   * 加载排序码
   * @return
   */
  @RequestMapping("loadDeptMaxOrderNum")
  public Map<String,Object> loadDeptMaxOrderNum(){
    Map<String,Object> map = new HashMap<String,Object>();
    QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("ordernum");
    IPage<Dept> page = new Page<>(1,1);
    List<Dept> list = deptService.page(page,queryWrapper).getRecords();
    if (list.size()>0){
      map.put("value",list.get(0).getOrdernum()+1);
    }else {
      map.put("value",1);
    }
    return map;
  }

  /**
   * 更新部门
   * @param deptVo
   * @return
   */
  @RequestMapping("updateDept")
  public ResultObj updateDept(DeptVo deptVo){
    try {
      deptService.updateById(deptVo);


      return ResultObj.UPDATE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.UPDATE_ERROR;
    }
  }

  /**
   * 检查当前部门是否有子部门
   * @param deptVo
   * @return
   */
  @RequestMapping("checkDeptHasChildrenNode")
  public Map<String,Object> checkDeptHasChildrenNode(DeptVo deptVo){
    Map<String,Object> map = new HashMap<String, Object>();
    QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("pid",deptVo.getDeptId());
    List<Dept> list = deptService.list(queryWrapper);
    if (list.size()>0){
      map.put("value",true);
    }else {
      map.put("value",false);
    }
    return map;
  }

  /**
   * 删除部门
   * @param deptVo
   * @return
   */
  @RequestMapping("deleteDept")
  public ResultObj deleteDept(DeptVo deptVo){
    try {
      deptService.removeById(deptVo.getDeptId());
      return ResultObj.DELETE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.DELETE_ERROR;
    }
  }
}
