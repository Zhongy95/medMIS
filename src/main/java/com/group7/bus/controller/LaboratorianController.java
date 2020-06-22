package com.group7.bus.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/laboratorian")
@RequiresRoles("LABORATORIAN")
public class LaboratorianController {


    /**跳转到检查挂号 */
    @RequestMapping("toExamRegister")
    public String toExamRegister(){return  "business/exam/doctorExamRegister";}

    /**跳转到检查挂号排队 */
    @RequestMapping("toExamRegisterQueue")
    public String toExamRegisterQueue(){return  "business/exam/doctorExamQueue";}

    /**跳转到检查挂号排队 */
    @RequestMapping("toExamManager")
    public String toExamManage(){return  "business/exam/doctorExamManager";}

    /**跳转到检查结果查看 */
    @RequestMapping("toExamDocManager")
    public String toExamDocManage(){return  "business/exam/doctorExamDocManager";}


}
