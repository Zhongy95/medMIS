package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.Payment;
import com.group7.bus.entity.Paymentitem;
import com.group7.bus.entity.Register;
import com.group7.bus.service.PaymentService;
import com.group7.bus.service.PaymentitemService;
import com.group7.bus.vo.PaymentVo;
import com.group7.sys.common.Constast;
import com.group7.sys.common.DataGridView;
import com.group7.sys.common.ResultObj;
import com.group7.sys.common.WebUtils;
import com.group7.sys.entity.Dept;
import com.group7.sys.entity.User;
import com.group7.sys.exception.medMISException;
import com.group7.sys.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 缴费单 前端控制器
 * </p>
 *
 * @author TT
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/api/bus/payment")
@RequiresRoles(value={"PATIENT","DOCTOR"}, logical = Logical.OR)
public class PaymentController {
    @Autowired private PaymentService paymentService;

    @Autowired private PaymentitemService paymentitemService;

    @Autowired private UserService userService;

    /**
     * 查询指定病人的所有挂号缴费记录
     *
     * @param paymentVo
     * @return
     */
    @RequestMapping("loadRegisterPayment")
    public DataGridView loadRegisterPayment(PaymentVo paymentVo) {
        IPage<Payment> page = new Page<>(paymentVo.getPage(), paymentVo.getLimit());
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        User user = (User)WebUtils.getSession().getAttribute("user");

        queryWrapper.eq("patient_id", user.getUserId())
            .eq("paymentitem_id", Constast.PAYMENT_REGISTER)
            .orderByDesc("createtime"); // 排序依据


        this.paymentService.page(page, queryWrapper);
        for(Payment payment:page.getRecords()){
            Paymentitem paymentitem = this.paymentitemService.getById(payment.getPaymentitemId());
            payment.setPaymentitemName(paymentitem.getPaymentitemName());
            User userpay = this.userService.getById(payment.getPaymentitemId());
            payment.setPatientName(userpay.getName());
        }

        return new DataGridView(page.getTotal(), page.getRecords());
    }


    /**
     * 单笔支付
     *
     * @param paymentVo
     * @return
     */
    @RequestMapping("payForOne")
    public ResultObj payForOne(PaymentVo paymentVo) throws medMISException {
        try {
            paymentVo.setIfdone(true);
            paymentVo.setDonetime(new Date());
            this.paymentService.saveOrUpdate(paymentVo);
            return ResultObj.PAY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("支付失败", HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * 批量支付
     *
     * @param paymentVo
     * @return
     */
    @RequestMapping("payForAll")
    public ResultObj payForAll(PaymentVo paymentVo) throws medMISException {
        try {
            //建立查询
            IPage<Payment> page = new Page<>(paymentVo.getPage(), paymentVo.getLimit());
            QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
            User user = (User)WebUtils.getSession().getAttribute("user");
            queryWrapper.eq("patient_id", user.getUserId())
                    .eq("paymentitem_id", Constast.PAYMENT_REGISTER);

            //完成查询，内容装到page里
            this.paymentService.page(page, queryWrapper);
            //遍历List，完成每一个payment更改
            for(Payment payment: page.getRecords()) {
                if(payment.getIfdone()!=true){
                    payment.setIfdone(true);
                    payment.setDonetime(new Date());
                    this.paymentService.updateById(payment);
                }
            }
            //this.paymentService.updateBatchById(page.getRecords());
            return ResultObj.PAY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("支付失败", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("addPayment")
    public ResultObj addPayment(PaymentVo paymentVo) throws medMISException {
        try {
            this.paymentService.save(paymentVo);
            return ResultObj.PAY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new medMISException("支付失败", HttpStatus.UNAUTHORIZED);
        }
    }



}