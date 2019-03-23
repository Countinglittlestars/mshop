package com.skymall.enums;

public enum ExceptionEnums {

    VALIDATE_FAILED(404, "参数校验失败"),
    UNAUTHORIZED(401, "认证"),
    FORBIDDEN(403, "未授权"),
    UNFINDBRAND_BY_ID(500, "无法根据品牌ID找到对应的品牌");


    private Integer ecode;

    private String emsg;

    ExceptionEnums(Integer ecode, String emsg){
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public Integer getEcode() {
        return ecode;
    }

    public void setEcode(Integer ecode) {
        this.ecode = ecode;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }
}
