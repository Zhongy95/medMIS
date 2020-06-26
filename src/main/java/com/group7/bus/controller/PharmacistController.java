package com.group7.bus.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/pharmacist")
@RequiresRoles("PHARMACIST")
public class PharmacistController {

    /** 跳转到治疗排队 */
    @RequestMapping("toMedQueueManager")
    public String toMedQueue() {
        return "business/med/doctorMedQueueManager";
    }

    /** 跳转到治疗结果查看 */
    @RequestMapping("toMedDocManager")
    public String toMedDocManager() {
        return "business/med/doctorMedDocManager";
    }

    /**跳转到治疗结果编写 */
    @RequestMapping("toMedDocWriter")
    public String toMedDocWriter(){return  "business/med/doctorMedDocWriter";}

}
