package com.skymall.vo;

import com.skymall.component.AbstractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Response <T> extends AbstractResponse {
    static Logger logger = LoggerFactory.getLogger(Response.class);
    private static String SUCCESS = "请求成功";
    private static String ERROR = "请求失败";
    private Integer errno;
    private String msg;
    private Object data;

    public Response(){

    }

    public static Response success(){
        Response response = new Response();
        response.setMsg(SUCCESS);
        response.setErrno(0);
        return response;
    }

    public static Response success(Object data) {
        logger.info(data.toString());
        Response response = new Response();
        response.data = data;
        response.setErrno(0);
        return response;
    }

    public static Response success(Object data, String msg){
        Response response = new Response();
        response.setMsg(msg);
        response.setErrno(0);
        response.setData(data);
        return response;
    }
    public static Response success(Object data, String msg, Integer code){
        Response response = new Response();
        response.setMsg(msg);
        response.setErrno(code);
        response.setData(data);
        return response;
    }
    public static Response error(Integer code, String msg){
        Response response = new Response();
        response.setMsg(msg);
        response.setErrno(code);
        return response;
    }


    public static Response error(){
        Response response = new Response();
        response.setMsg(ERROR);
        response.setErrno(500);
        return response;
    }

    public static Response error(String msg) {
        Response response  = new Response();
        response.setErrno(500);
        response.setMsg(msg);
        return response;
    }


    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
