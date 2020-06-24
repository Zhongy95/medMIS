package com.group7.bus.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/nurse")
@RequiresRoles("NURSE")
public class NurseController {

    /** 跳转到治疗排队 */
    @RequestMapping("toTreatQueueManage")
    public String toTreatQueue() {
        return "business/treat/doctorTreatQueueManager";
    }

    /** 跳转到治疗结果查看 */
    @RequestMapping("toTreatDocManager")
    public String toTreatDocManager() {
        return "business/treat/doctorTreatDocManager";
    }

    /**跳转到治疗结果编写 */
    @RequestMapping("toTreatDocWriter")
    public String toExamDocWriter(){return  "business/nurse/doctorTreatDocWriter";}

}
