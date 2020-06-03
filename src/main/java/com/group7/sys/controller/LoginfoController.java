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
@RequestMapping("/loginfo")
public class LoginfoController {

  @Autowired private LoginfoService loginfoService;

  /** 全查询 */
  @RequestMapping("loadAllLoginfo")
  public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {

    IPage<Loginfo> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
    QueryWrapper<Loginfo> querywrapper = new QueryWrapper<Loginfo>();
    querywrapper.like(
        StringUtils.isNotBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
    querywrapper.like(
        StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip", loginfoVo.getLoginip());
    querywrapper.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
    querywrapper.ge(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getEndTime());
    querywrapper.orderByDesc("logintime"); // 排序依据
    this.loginfoService.page(page, querywrapper);

    return new DataGridView(page.getTotal(), page.getRecords());
  }

  /**
   * 删除
   *
   * @param id
   * @return
   */
  @RequestMapping("deleteLoginfo")
  public ResultObj deleteLoginfo(Integer id) {
    try {
      this.loginfoService.removeById(id);
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
