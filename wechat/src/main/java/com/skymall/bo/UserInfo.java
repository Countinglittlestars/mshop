package com.skymall.bo;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String avatarUrl;
    //
    private String city;
    //
    private Integer gender;
    //
    private String nickName;
    //
    private String province;
}
