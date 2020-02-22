package com.pili.syang.enums;

public enum StatusCode {
    SUCCESS(200,"成功"),
    WONG_PASSWORD(401,"账号或者密码错误"),
    EMAIL_USED(402,"邮箱已被占用"),
    NEED_LOGIN(404,"请登录！"),
    UNKNOWN_ERROR(999,"未知错误"),
    DEFEAT(400,"失败");
    private int type;
    private String massge;

    public int getType() {
        return type;
    }

    public String getMassge() {
        return massge;
    }

    StatusCode(int type, String name) {
        this.type = type;
        this.massge = name;
    }
}
