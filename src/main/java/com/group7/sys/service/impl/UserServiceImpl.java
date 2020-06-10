package com.group7.sys.service.impl;

import com.group7.sys.entity.User;
import com.group7.sys.mapper.RoleMapper;
import com.group7.sys.mapper.UserMapper;
import com.group7.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 *
 * @author Robin
 * @since 2020-06-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired private UserMapper userMapper;

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }


    


}
