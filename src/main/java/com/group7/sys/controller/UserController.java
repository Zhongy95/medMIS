package com.group7.sys.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.MedMISApplication;
import com.group7.sys.common.*;
import com.group7.sys.entity.Dept;
import com.group7.sys.entity.Role;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.DeptService;
import com.group7.sys.service.RoleService;
import com.group7.sys.service.UserService;
import com.group7.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.One;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端控制器
 *
 * @author Robin
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/api/sys/user/")
public class UserController {

    @Autowired private UserService userService;

    @Autowired private DeptService deptService;

    @Autowired private RoleService roleService;

    /**
     * 用户全查询
     *
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllDoctor")
    public DataGridView loadAllDocter(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //根据用户登录名称以及用户名称模糊查询用户
        queryWrapper.like(StringUtils.isNotBlank(userVo.getName()),"login_name",userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()),"user_name",userVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getAddr()),"addr",userVo.getAddr());
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getDeptId()), "dept_id", userVo.getDeptId());
        // 限定查询用户类型为医生
        if(userVo.getRoleId()==null) {
//            queryWrapper.and(
//                    queryWrapper.eq("role_id", Constast.ROLE_DOCTOR).or().eq("role_id", Constast.ROLE_LABORATORIAN).or().eq("role_id", Constast.ROLE_ADMIN).or().eq("role_id", Constast.ROLE_PHARMACIST).or().eq("role_id", Constast.ROLE_NURSE));
//
            queryWrapper.ne("role_id",Constast.ROLE_PATIENT);
        }
        else {
            queryWrapper.eq("role_id",userVo.getRoleId());
        }

        queryWrapper.orderByDesc("user_id");// 排序依据
        userService.page(page,queryWrapper);


        //将所有用户数据放入list中
        List<User> list = page.getRecords();
        for (User user : list) {
            String deptId = user.getDeptId();
            if (deptId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                Integer deptIdint=Integer.valueOf(deptId);
                Dept one = deptService.getById(deptIdint);
                //设置user的部门名称
                user.setDeptName(one.getDeptName());
            }
            Integer roleId = user.getRoleId();
            if (roleId!=null){
                QueryWrapper<Role> queryWrapperrole = new QueryWrapper<>();
                queryWrapperrole.eq("role_id",roleId);
                Role one = roleService.getOne(queryWrapperrole);
                //设置user的角色名称
                user.setRoleName(one.getRoleName());
            }
        }
        return new DataGridView(page.getTotal(),list);
    }



    /**
     * 根据部门ID查询用户
     * @param deptId
     * @return
     */
    @RequestMapping("loadUsersByDeptId")
    public DataGridView loadUsersByDeptIp(Integer deptId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(deptId!=null,"dept_id",deptId);
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        queryWrapper.ne("role_id",Constast.ROLE_PATIENT);
        List<User> list = userService.list(queryWrapper);
        for (User user : list) {
            System.out.println(user.toString());
        }
        return new DataGridView(list);
    }


    @RequestMapping("loadUser")
    public User loadUser() throws medMISException {
        try {
            User user = (User) WebUtils.getSession().getAttribute("user");
            return user;
        } catch (Exception e) {
            throw new medMISException("读取信息失败", HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * 把用户名转成拼音
     * @param username
     * @return
     */
//    @RequestMapping("changeChineseToPinyin")
//    public Map<String,Object> changeChineseToPinyin(String username){
//        Map<String,Object> map = new HashMap<String, Object>(16);
//        if (null!=username){
//            map.put("value", PinyinUtils.getPingYin(username));
//        }else {
//            map.put("value","");
//        }
//        return map;
//    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo){
        try {
            //设置类型
            userVo.setRoleId(userVo.getRoleId());
            //设置密码
            userVo.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
            userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id查询一个用户
     * @param id  领导的id
     * @return
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id){
        return new DataGridView(userService.getById(id));
    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo){
        try {
            //避免修改到密码
            User user = userService.getById(userVo.getUserId());
            userVo.setPassword(user.getPassword());

            userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping("deleteUser/{userId}")
    public ResultObj deleteUser(@PathVariable("userId") Integer userId){
        try {
            userService.removeById(userId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改用户密码
     * @param userVo
     * @return
     */
    @RequestMapping("changePwd")
    public ResultObj changePwd(UserVo userVo){
        try {
            //避免修改到密码外的其他数据
            User user = userService.getById(userVo.getUserId());
            if(userVo.getPassword().equals(user.getPassword())) {
                //防止误触
                user.setPassword(userVo.getPassword());
            }else {
                user.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
            }
            userService.updateById(user);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 重置用户密码
     * @param id
     * @return
     */
//    @RequestMapping("resetPwd/{id}")
//    public ResultObj resetPwd(@PathVariable("id") Integer id){
//        try {
//            User user = new User();
//            user.setUserId(id);
//            //设置密码
//            user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD,salt,2).toString());
//            userService.updateById(user);
//            return ResultObj.RESET_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultObj.RESET_ERROR;
//        }
//    }

    /**
     * 根据用户id查询角色并选中已拥有的角色
     * @param id 用户id
     * @return
     */
//    @RequestMapping("initRoleByUserId")
//    public DataGridView initRoleByUserId(Integer id){
//        //1.查询所有可用的角色
//        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
//        List<Map<String, Object>> listMaps = roleService.listMaps(queryWrapper);
//        //2.查询当前用户拥有的角色ID集合
//        List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(id);
//        for (Map<String, Object> map : listMaps) {
//            Boolean LAY_CHECKED=false;
//            Integer roleId = (Integer) map.get("id");
//            for (Integer rid : currentUserRoleIds) {
//                //如果当前用户已有该角色，则让LAY_CHECKED为true。LAY_CHECKED为true时，复选框选中
//                if (rid.equals(roleId)){
//                    LAY_CHECKED=true;
//                    break;
//                }
//            }
//            map.put("LAY_CHECKED",LAY_CHECKED);
//        }
//        return new DataGridView(Long.valueOf(listMaps.size()),listMaps);
//    }

    /**
     * 保存用户和角色的关系
     * @param uid 用户的ID
     * @param ids 用户拥有的角色的ID的数组
     * @return
     */
//    @RequestMapping("saveUserRole")
//    public ResultObj saveUserRole(Integer uid,Integer[] ids){
//        try {
//            userService.saveUserRole(uid,ids);
//            return ResultObj.DISPATCH_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultObj.DISPATCH_ERROR;
//        }
//    }

    /**
     * 修改用户的密码
     * @param oldPassword  用户的原密码
     * @param newPwdOne     用户第一次输入的新密码
     * @param newPwdTwo     用户第二次输入的新密码
     * @return
     */
//    @RequestMapping("changePassword")
//    public ResultObj changePassword(String oldPassword,String newPwdOne,String newPwdTwo){
//        //1.先通过session获得当前用户的ID
//        User user =(User) WebUtils.getSession().getAttribute("user");
//        //2.将oldPassword加盐并散列两次在和数据库中的密码进行对比
//        Integer userId = user.getId();
//        User user1 = userService.getById(userId);
//        //2.1获得该用户的盐
//        String salt = user1.getSalt();
//        //2.2通过用户输入的原密码，从数据库中查出的盐，散列次数生成新的旧密码
//        String oldPassword2 = new Md5Hash(oldPassword,salt,Constast.HASHITERATIONS).toString();
//        if (oldPassword2.equals(user1.getPwd())){
//            if (newPwdOne.equals(newPwdTwo)){
//                //3.生成新的密码
//                String newPassword = new Md5Hash(newPwdOne,salt,Constast.HASHITERATIONS).toString();
//                user1.setPwd(newPassword);
//                userService.updateById(user1);
//                return ResultObj.UPDATE_SUCCESS;
//            }else {
//                return ResultObj.UPDATE_ERROR;
//            }
//        }else {
//            return ResultObj.UPDATE_ERROR;
//        }
//    }

    /**
     * 返回当前已登录的user
     * @return
     */
//    @RequestMapping("getNowUser")
//    public User getNowUser(){
//        //1.获取当前session中的user
//        User user = (User) WebUtils.getSession().getAttribute("user");
//        System.out.println("*****************************************");
//        System.out.println(user);
//        return user;
//    }
//
//
//    /**
//     * 修改用户
//     * @param userVo
//     * @return
//     */
//    @RequestMapping("updateUserInfo")
//    public ResultObj updateUserInfo(UserVo userVo){
//        try {
//            //用户头像不是默认图片
//            if (!(userVo.getImgpath()!=null&&userVo.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){
//                if (userVo.getImgpath().endsWith("_temp")){
//                    String newName = AppFileUtils.renameFile(userVo.getImgpath());
//                    userVo.setImgpath(newName);
//                    //删除原先的图片
//                    String oldPath = userService.getById(userVo.getId()).getImgpath();
//                    AppFileUtils.removeFileByPath(oldPath);
//                    //获取存储在session中的user并重新设置user中的图片地址
//                    User user = (User) WebUtils.getSession().getAttribute("user");
//                    user.setImgpath(newName);
//                    //重新设置user
//                    WebUtils.getSession().setAttribute("user",user);
//                }
//            }
//            userService.updateById(userVo);
//            return ResultObj.UPDATE_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultObj.UPDATE_ERROR;
//        }
//    }
}
