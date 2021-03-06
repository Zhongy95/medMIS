package com.group7.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Notice;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.NoticeService;
import com.group7.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 前端控制器
 *
 * @author Robin
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/api/notice/")
@RequiresRoles("ADMIN")
public class NoticeController {

  @Autowired private NoticeService noticeService;

  /**
   * 查询
   *
   * @param noticeVo
   * @return
   */
  @RequestMapping("loadAllNotice")
  public DataGridView loadAllNotice(NoticeVo noticeVo) {
    IPage<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit());

    QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
    // 输入给定查询条件，默认无
    queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
    queryWrapper.like(
        StringUtils.isNotBlank(noticeVo.getOperName()), "oper_name", noticeVo.getOperName());
    queryWrapper.ge(noticeVo.getStartTime() != null, "create_time", noticeVo.getStartTime());
    queryWrapper.le(noticeVo.getEndTime() != null, "create_time", noticeVo.getEndTime());
    queryWrapper.orderByDesc("create_time"); // 排序依据

    this.noticeService.page(page, queryWrapper);

    return new DataGridView(page.getTotal(), page.getRecords());
  }

  /**
   * 添加
   *
   * @param noticeVo
   * @return
   */
  @RequestMapping("addNotice")
  public ResultObj addNotice(NoticeVo noticeVo) throws medMISException {
    try {
      noticeVo.setCreateTime(new Date());
      User user = (User) WebUtils.getSession().getAttribute("user");
      noticeVo.setOperName(user.getName());
      this.noticeService.saveOrUpdate(noticeVo);
      return ResultObj.ADD_SUCCESS;

    } catch (Exception e) {
      e.printStackTrace();
      throw new medMISException("添加失败", HttpStatus.UNAUTHORIZED);
    }
  }

  /**
   * 更改
   *
   * @param noticeVo
   * @return
   */
  @RequestMapping("updateNotice")
  public ResultObj updateNotice(NoticeVo noticeVo) throws medMISException {
    try {
      this.noticeService.updateById(noticeVo);
      return ResultObj.UPDATE_SUCCESS;

    } catch (Exception e) {
      e.printStackTrace();
      throw new medMISException("更改失败", HttpStatus.UNAUTHORIZED);
    }
  }
  /**
   * 删除公告
   *
   * @param noticeVo
   * @return
   */
  @RequestMapping("deleteNotice")
  public ResultObj deleteNotice(NoticeVo noticeVo) throws medMISException {
    try {
      noticeService.removeById(noticeVo);
      return ResultObj.DELETE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
    }
  }

  /**
   * 批量删除公告
   *
   * @param noticeVo
   * @return
   */
  @RequestMapping("batchDeleteNotice")
  public ResultObj batchDeleteNotice(NoticeVo noticeVo) throws medMISException {
    try {
      Collection<Serializable> idList = new ArrayList<>();
      for (Integer id : noticeVo.getIds()) {
        idList.add(id);
      }
      noticeService.removeByIds(idList);
      return ResultObj.DELETE_SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      throw new medMISException("删除失败", HttpStatus.UNAUTHORIZED);
    }
  }
}
