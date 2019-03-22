package com.skymall.exception;

import com.skymall.enums.ExceptionEnums;

/**
 * 自定义异常
 *
 * @author lipengjun
 *
 */
public class AdminException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errmsg;
    private int errno = 500;

    public AdminException(String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
    }

    public AdminException(ExceptionEnums exceptionEnums){
        this.errmsg = exceptionEnums.getEmsg();
        this.errno = exceptionEnums.getEcode();
    }

    public AdminException(String errmsg, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
    }

    public AdminException(String errmsg, int errno) {
        super(errmsg);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public AdminException(String errmsg, int errno, Throwable e) {
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
