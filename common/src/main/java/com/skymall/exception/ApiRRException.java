package com.skymall.exception;

import com.skymall.enums.ExceptionEnums;

/**
 * 自定义异常
 *
 * @author lipengjun
 *
 */
public class ApiRRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errmsg;
    private int errno = 500;

    public ApiRRException(String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
    }

    public ApiRRException(ExceptionEnums exceptionEnums){
        this.errmsg = exceptionEnums.getEmsg();
        this.errno = exceptionEnums.getEcode();
    }

    public ApiRRException(String errmsg, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
    }

    public ApiRRException(String errmsg, int errno) {
        super(errmsg);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public ApiRRException(String errmsg, int errno, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}
