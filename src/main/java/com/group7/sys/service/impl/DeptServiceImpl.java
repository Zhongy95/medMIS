package com.group7.sys.service.impl;

import com.group7.sys.entity.Dept;
import com.group7.sys.mapper.DeptMapper;
import com.group7.sys.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author Robin
 * @since 2020-06-02
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {}
