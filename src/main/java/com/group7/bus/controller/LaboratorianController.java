package com.group7.bus.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/laboratorian")
@RequiresRoles("LABORATORIAN")
public class LaboratorianController {




    /**跳转到检查挂号排队 */
    @RequestMapping("toExamRegisterQueue")
    public String toExamRegisterQueue(){return  "business/exam/doctorExamQueueManager";}

    /**跳转到检查挂号 */
    @RequestMapping("toExamManager")
    public String toExamManage(){return  "business/exam/doctorExamRegisterManager";}

    /**跳转到检查结果查看 */
    @RequestMapping("toExamDocManager")
    public String toExamDocManage(){return  "business/exam/doctorExamDocManager";}


    /**跳转到检查结果编写 */
    @RequestMapping("toExamDocWriter")
    public String toExamDocWriter(){return  "business/exam/doctorExamDocWriter";}

}
