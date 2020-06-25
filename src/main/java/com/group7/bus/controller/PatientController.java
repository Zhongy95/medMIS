package com.group7.bus.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus/patient")
@RequiresRoles("PATIENT")
public class PatientController {

    /** 跳转到挂号时间 */
    @RequestMapping("toDoctorTimeManager")
    public String toDoctorTimeManager() {
        return "business/register/doctorTimeManager";
    }

    /** 跳转到挂号时间-左 */
    @RequestMapping("toDoctorTimeLeft")
    public String toDeptLeft() {
        return "business/register/doctorTimeLeft";
    }
    /** 跳转到挂号时间-右 */
    @RequestMapping("toDoctorTimeRight")
    public String toDeptRight() {
        return "business/register/doctorTimeRight";
    }

    /** 跳转到挂号管理 */
    @RequestMapping("toRegisterManager")
    public String toRegisterManager() {
        return "business/register/registerTimeManager";
    }

    /** 跳转到挂号缴费管理 */
    @RequestMapping("toPaymentManager")
    public String toPaymentManager() {
        return "business/paymentManager";
    }

    /** 跳转到排队治疗 */
    @RequestMapping("toRegisterQueue")
    public String toRegisterQueue() {
        return "business/record/patientRegisterQueue";
    }

    /** 跳转到病历查看 */
    @RequestMapping("toRecordManager")
    public String toRecordManager() {
        return "business/record/patientRecordManager";
    }


    /** 跳转到待做检查 */
    @RequestMapping("toExamToDoManager")
    public String toExamToDoManager() {
        return "business/record/patientExamtodoManager";
    }
    /** 跳转到病人检查排队 */
    @RequestMapping("toExamRegisterQueue")
    public String toExamRegisterQueue() { return "business/exam/patientExamQueueManager";
    }

    /** 跳转到待领药品 */
    @RequestMapping("toMedToDoManager")
    public String toMedToDoManager() {
        return "business/record/patientMedtodoManager";
    }

    /** 跳转到待做治疗 */
    @RequestMapping("toTreatToDoManager")
    public String toTreatToDoManager() {
        return "business/record/patientTreattodoManager";
    }

    /** 跳转到检查管理 */
    @RequestMapping("toExamManager")
    public String toExamManager() {
        return "business/exam/patientExamManager";
    }

    /** 跳转到检查结果查看 */
    @RequestMapping("toExamDocManager")
    public String toExamDocManager() {
        return "business/exam/patientExamDocManager";
    }

    /** 跳转到治疗排队 */
    @RequestMapping("toTreatQueueManager")
    public String toTreatQueue() {
        return "business/treat/patientTreatQueueManager";
    }

    /** 跳转到治疗结果查看 */
    @RequestMapping("toTreatDocManager")
    public String toTreatDocManager() {
        return "business/exam/patientTreatDocManager";
    }
}
