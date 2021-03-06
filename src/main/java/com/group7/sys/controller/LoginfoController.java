package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.entity.Loginfo;
import com.group7.sys.service.LoginfoService;
import com.group7.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 前端控制器
 *
 * @author Robin
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/api/loginfo/")
@RequiresRoles("ADMIN")
public class LoginfoController {

  @Autowired private LoginfoService loginfoService;

  /** 全查询 */
  @RequestMapping("loadAllLoginfo")
  public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {

    IPage<Loginfo> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
    QueryWrapper<Loginfo> querywrapper = new QueryWrapper<Loginfo>();
    querywrapper.like(
        StringUtils.isNotBlank(loginfoVo.getLoginName()), "login_name", loginfoVo.getLoginName());
    querywrapper.like(
        StringUtils.isNotBlank(loginfoVo.getLoginIp()), "login_ip", loginfoVo.getLoginIp());
    querywrapper.ge(loginfoVo.getStartTime() != null, "login_time", loginfoVo.getStartTime());
    querywrapper.ge(loginfoVo.getEndTime() != null, "login_time", loginfoVo.getEndTime());
    querywrapper.orderByDesc("login_time"); // 排序依据
    this.loginfoService.page(page, querywrapper);

    return new DataGridView(page.getTotal(), page.getRecords());
  }

  /**
   * 删除
   *
   * @param liId
   * @return
   */
  @RequestMapping("deleteLoginfo")
  public ResultObj deleteLoginfo(Integer liId) {
    try {
      this.loginfoService.removeById(liId);
      return ResultObj.DELETE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.DELETE_ERROR;
    }
  }

  /**
   * 批量删除
   *
   * @param loginfoVo
   * @return
   */
  @RequestMapping("batchDeleteLoginfo")
  public ResultObj batchdeleteLoginfo(LoginfoVo loginfoVo) {
    try {
      Collection<Serializable> idList = new ArrayList<Serializable>();
      for (Integer id : loginfoVo.getIds()) {
        idList.add(id);
      }

      this.loginfoService.removeByIds(idList);
      return ResultObj.DELETE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      return ResultObj.DELETE_ERROR;
    }
  }
}
