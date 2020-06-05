package com.group7.sys.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionDetails {
    // 出错信息详情
    private String message;

    // 时间戳
    private Date time;

    // 出错状态
    private String status;

    public ExceptionDetails(medMISException e) {
        this.message = e.getMessage();
        this.time = e.getTime();
        this.status = e.getStatus();
    }
}
