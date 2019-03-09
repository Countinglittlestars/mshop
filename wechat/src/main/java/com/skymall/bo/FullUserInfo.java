package com.skymall.bo;

import lombok.Data;

import java.io.Serializable;
@Data
public class FullUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //errMsg
    private String errMsg;
    //rawData
    private String rawData;
    //userInfo
    private UserInfo userInfo;
    //encryptedData
    private String encryptedData;
    //iv
    private String iv;
    //signature
    private String signature;

}
