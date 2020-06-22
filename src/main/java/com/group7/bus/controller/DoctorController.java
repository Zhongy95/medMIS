package com.group7.bus.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/doctor")
@RequiresRoles("DOCTOR")
public class DoctorController {

    /** 跳转到排队治疗 */
    @RequestMapping("toRegisterQueue")
    public String toRegisterQueue() { return "business/record/doctorRegisterQueue"; }

    /** 跳转到病历查看 */
    @RequestMapping("toRecordManager")
    public String toRecordManager() { return "business/record/doctorRecordManager"; }

    /** 跳转到病历编写 */
    @RequestMapping("toRecordWriter")
    public String toRecordWriter() { return "business/record/doctorRecordWriter"; }

    /** 跳转到检查单查看 */
    @RequestMapping("toTREATToDoManager")
    public String toTreattodoManager() { return "business/record/doctorTreattodoManager"; }

    /** 跳转到治疗单查看 */
    @RequestMapping("toExamToDoManager")
    public String toExamtodoManager() { return "business/record/doctorExamtodoManager"; }

    /** 跳转到药品单查看 */
    @RequestMapping("toMedToDoManager")
    public String toMedtodoManager() { return "business/record/doctorMedtodoManager"; }



}
