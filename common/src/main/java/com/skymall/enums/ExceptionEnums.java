package com.skymall.enums;

public enum ExceptionEnums {

    VALIDATE_FAILED(404, "参数校验失败"),
    UNAUTHORIZED(401, "认证"),
    FORBIDDEN(403, "未授权"),
    UPDATEFAILED(500,"修改失败"),
    NOTUNIQUE(500,"名称重复"),
    NOTFOUND(500,"数据不存在");


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
