package com.pili.syang;

import com.pili.syang.enums.StatusCode;
import lombok.Data;

@Data
public class BaseInfo {
    private int code;
    private String prompt;
    private Object data;
    public static BaseInfo BackInfo(StatusCode statusCode, Object data){
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setPrompt(statusCode.getMassge());
        baseInfo.setData(data);
        baseInfo.setCode(statusCode.getType());
        return baseInfo;
    }
}