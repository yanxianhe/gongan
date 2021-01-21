package com.example.gongan.pojo.UserPram;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("上传居民信息")
public class UserParm implements Serializable {
    private static final long serialVersionUID = -2263873979997509419L;

    @ApiModelProperty(value = "居民姓名")
    private String RESIDENT_NAME;
    //人员头像base64
    @ApiModelProperty(value = "人员头像base64", hidden=true)
    private String IDCARD_FACE;
    @ApiModelProperty(value = "国籍代码")
    private String CITIZENSHIP_CODE;
    @ApiModelProperty(value = "证件类型")
    private String IDCARD_ZJLX;
    @ApiModelProperty(value = "证件号码")
    private String IDCARD_ZJHM;
    @ApiModelProperty(value = "手机号码")
    private String PHONE_NUM;
    @ApiModelProperty(value = "居住地址")
    private String JZD_ADDRESS;

    @ApiModelProperty(value = "人员头像")
    private MultipartFile IDCARD_FACES;

    public UserParm() {
    }

    public UserParm(String RESIDENT_NAME, String IDCARD_FACE, String CITIZENSHIP_CODE, String IDCARD_ZJLX, String IDCARD_ZJHM, String PHONE_NUM, String JZD_ADDRESS) {
        this.RESIDENT_NAME = RESIDENT_NAME;
        this.IDCARD_FACE = IDCARD_FACE;
        this.CITIZENSHIP_CODE = CITIZENSHIP_CODE;
        this.IDCARD_ZJLX = IDCARD_ZJLX;
        this.IDCARD_ZJHM = IDCARD_ZJHM;
        this.PHONE_NUM = PHONE_NUM;
        this.JZD_ADDRESS = JZD_ADDRESS;
    }

    public String getRESIDENT_NAME() {
        return RESIDENT_NAME;
    }

    public void setRESIDENT_NAME(String RESIDENT_NAME) {
        this.RESIDENT_NAME = RESIDENT_NAME;
    }

    public String getIDCARD_FACE() {
        return IDCARD_FACE;
    }

    public void setIDCARD_FACE(String IDCARD_FACE) {
        this.IDCARD_FACE = IDCARD_FACE;
    }

    public String getCITIZENSHIP_CODE() {
        return CITIZENSHIP_CODE;
    }

    public void setCITIZENSHIP_CODE(String CITIZENSHIP_CODE) {
        this.CITIZENSHIP_CODE = CITIZENSHIP_CODE;
    }

    public String getIDCARD_ZJLX() {
        return IDCARD_ZJLX;
    }

    public void setIDCARD_ZJLX(String IDCARD_ZJLX) {
        this.IDCARD_ZJLX = IDCARD_ZJLX;
    }

    public String getIDCARD_ZJHM() {
        return IDCARD_ZJHM;
    }

    public void setIDCARD_ZJHM(String IDCARD_ZJHM) {
        this.IDCARD_ZJHM = IDCARD_ZJHM;
    }

    public String getPHONE_NUM() {
        return PHONE_NUM;
    }

    public void setPHONE_NUM(String PHONE_NUM) {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getJZD_ADDRESS() {
        return JZD_ADDRESS;
    }

    public void setJZD_ADDRESS(String JZD_ADDRESS) {
        this.JZD_ADDRESS = JZD_ADDRESS;
    }

    public MultipartFile getIDCARD_FACES() {
        return IDCARD_FACES;
    }

    public void setIDCARD_FACES(MultipartFile iDCARD_FACES) {
        IDCARD_FACES = iDCARD_FACES;
    }
}
