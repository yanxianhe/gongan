package com.example.gongan.pojo.UserPram;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/23 17:46
 */

@ApiModel("上传智慧门禁数据")
public class UploadSmartAccessControlData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "* RID(居民ID)")
    private String RESIDENT_ID;
    @ApiModelProperty(value = "* 人脸照片base64", hidden=true)
    private String FACE;
    @ApiModelProperty(value = "* 开门时间")
    private String CREATETIME;
    @ApiModelProperty(value = "* 开门方式")
    private String OPENTYPE;
    @ApiModelProperty(value = "* 设备编码")
    private String DEVICE_NUMBER;
    @ApiModelProperty(value = "* 开门状态")
    private String OPEN_STATUS;
    @ApiModelProperty(value = "人脸图片")
    private MultipartFile FACE_IMAGES;

    public String getRESIDENT_ID() {
        return RESIDENT_ID;
    }

    public void setRESIDENT_ID(String rESIDENT_ID) {
        RESIDENT_ID = rESIDENT_ID;
    }

    public String getFACE() {
        return FACE;
    }

    public void setFACE(String fACE) {
        FACE = fACE;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String cREATETIME) {
        CREATETIME = cREATETIME;
    }

    public String getOPENTYPE() {
        return OPENTYPE;
    }

    public void setOPENTYPE(String oPENTYPE) {
        OPENTYPE = oPENTYPE;
    }

    public String getDEVICE_NUMBER() {
        return DEVICE_NUMBER;
    }

    public void setDEVICE_NUMBER(String dEVICE_NUMBER) {
        DEVICE_NUMBER = dEVICE_NUMBER;
    }

    public String getOPEN_STATUS() {
        return OPEN_STATUS;
    }

    public void setOPEN_STATUS(String oPEN_STATUS) {
        OPEN_STATUS = oPEN_STATUS;
    }

    public UploadSmartAccessControlData(){

    }

    public UploadSmartAccessControlData(String rESIDENT_ID, String fACE, String cREATETIME, String oPENTYPE,
            String dEVICE_NUMBER, String oPEN_STATUS) {
        RESIDENT_ID = rESIDENT_ID;
        FACE = fACE;
        CREATETIME = cREATETIME;
        OPENTYPE = oPENTYPE;
        DEVICE_NUMBER = dEVICE_NUMBER;
        OPEN_STATUS = oPEN_STATUS;
    }

    @Override
    public String toString() {
        return "UploadSmartAccessControlData [CREATETIME=" + CREATETIME + ", DEVICE_NUMBER=" + DEVICE_NUMBER + ", FACE="
                + FACE + ", OPENTYPE=" + OPENTYPE + ", OPEN_STATUS=" + OPEN_STATUS + ", RESIDENT_ID=" + RESIDENT_ID
                + "]";
    }

    

    
    
}
