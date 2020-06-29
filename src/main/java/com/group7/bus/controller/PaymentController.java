package com.group7.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group7.bus.entity.*;
import com.group7.bus.service.*;
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
import org.apache.shiro.crypto.JcaCipherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.group7.sys.common.Constast.*;

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

    @Autowired private TreattodoService treattodoService;

    @Autowired private RegisterService registerService;

    @Autowired private ExamregisterService examregisterService;

    @Autowired private MedtodoService medtodoService;
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
            .orderByDesc("createtime"); // 排序依据
        this.paymentService.page(page, queryWrapper);
        for(Payment payment:page.getRecords()){
            Paymentitem paymentitem = this.paymentitemService.getById(payment.getPaymentitemId());
            payment.setPaymentitemName(paymentitem.getPaymentitemName());
            User userpay = this.userService.getById(payment.getPatientId());
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
            Payment payment =this.paymentService.getById(paymentVo);
            payment.setIfdone(true);
            payment.setDonetime(new Date());
            this.paymentService.saveOrUpdate(payment);
            if(payment.getPaymentitemId().equals(PAYMENT_REGISTER))
            {
                //如果是挂号项，完成挂号项的更新
                QueryWrapper<Register> queryWrapperRegister = new QueryWrapper<>();
                queryWrapperRegister.eq("payment_id",payment.getPaymentId());
                Register registerUpdate = this.registerService.getOne(queryWrapperRegister);
                registerUpdate.setPaymentIfdone(true);
                this.registerService.updateById(registerUpdate);
            }
            else if(payment.getPaymentitemId().equals(PAYMENT_EXAM))
            {
                QueryWrapper<Examregister> examregisterQueryWrapper = new QueryWrapper<>();
                examregisterQueryWrapper.eq("payment_id",payment.getPaymentId());
                Examregister examregisterUpdate =this.examregisterService.getOne(examregisterQueryWrapper);
                examregisterUpdate.setPaymentIfdone(true);
                this.examregisterService.updateById(examregisterUpdate);
            }
            else if(payment.getPaymentitemId().equals(PAYMENT_TREATMENT))
            {
                //如果是治疗项，完成treattodo的更新
                QueryWrapper<Treattodo> queryWrapperTreat = new QueryWrapper<>();
                queryWrapperTreat.eq("payment_id",payment.getPaymentId());
                Treattodo treattodoUpdate = this.treattodoService.getOne(queryWrapperTreat);
                treattodoUpdate.setPayIfdone(true);
                this.treattodoService.updateById(treattodoUpdate);
            }
            else if(payment.getPaymentitemId().equals(PAYMENT_MEDICINE))
            {
                //如果是治疗项，完成treattodo的更新
                QueryWrapper<Medtodo> queryWrapperMed = new QueryWrapper<>();
                queryWrapperMed.eq("payment_id",payment.getPaymentId());
                Medtodo medtodoUpdate = this.medtodoService.getOne(queryWrapperMed);
                medtodoUpdate.setPayIfdone(true);
                this.medtodoService.updateById(medtodoUpdate);
            }

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
            //IPage<Payment> page = new Page<>(paymentVo, paymentVo.getLimit());
            QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
            User user = (User)WebUtils.getSession().getAttribute("user");
            queryWrapper.eq("patient_id", user.getUserId())
                    .eq("ifdone", false);

            //完成查询，内容装到page里
            this.paymentService.list(queryWrapper);
            //遍历List，完成每一个payment更改
            for(Payment payment: this.paymentService.list()) {
                if(!payment.getIfdone()){
                    payment.setIfdone(true);
                    payment.setDonetime(new Date());
                    this.paymentService.updateById(payment);
                    if(payment.getPaymentitemId().equals(PAYMENT_REGISTER))
                    {
                        //如果是挂号项，完成挂号项的更新
                        QueryWrapper<Register> queryWrapperRegister = new QueryWrapper<>();
                        queryWrapperRegister.eq("payment_id",payment.getPaymentId());
                        Register registerUpdate = this.registerService.getOne(queryWrapperRegister);
                        registerUpdate.setPaymentIfdone(true);
                        this.registerService.updateById(registerUpdate);
                    }
                    else if(payment.getPaymentitemId().equals(PAYMENT_EXAM))
                    {
                        QueryWrapper<Examregister> examregisterQueryWrapper = new QueryWrapper<>();
                        examregisterQueryWrapper.eq("payment_id",payment.getPaymentId());
                        Examregister examregisterUpdate =this.examregisterService.getOne(examregisterQueryWrapper);
                        examregisterUpdate.setPaymentIfdone(true);
                        this.examregisterService.updateById(examregisterUpdate);
                    }
                    else if(payment.getPaymentitemId().equals(PAYMENT_TREATMENT))
                    {
                        //如果是治疗项，完成treattodo的更新
                        QueryWrapper<Treattodo> queryWrapperTreat = new QueryWrapper<>();
                        queryWrapperTreat.eq("payment_id",payment.getPaymentId());
                        Treattodo treattodoUpdate = this.treattodoService.getOne(queryWrapperTreat);
                        treattodoUpdate.setPayIfdone(true);
                        this.treattodoService.updateById(treattodoUpdate);
                    }
                    else if(payment.getPaymentitemId().equals(PAYMENT_MEDICINE))
                    {
                        //如果是治疗项，完成treattodo的更新
                        QueryWrapper<Medtodo> queryWrapperMed = new QueryWrapper<>();
                        queryWrapperMed.eq("payment_id",payment.getPaymentId());
                        Medtodo medtodoUpdate = this.medtodoService.getOne(queryWrapperMed);
                        medtodoUpdate.setPayIfdone(true);
                        this.medtodoService.updateById(medtodoUpdate);
                    }
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