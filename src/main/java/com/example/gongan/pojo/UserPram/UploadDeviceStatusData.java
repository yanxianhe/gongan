package com.example.gongan.pojo.UserPram;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/23 17:46
 */

@ApiModel("上传设备状态")
public class UploadDeviceStatusData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "* 设备编码")
    private String DEV_NUMBER;
    @ApiModelProperty(value = "* 设备状态")
    private String DEV_STATUS;
    @ApiModelProperty(value = "上传时间 默认系统时间")
    private String UPLOAD_TIME;

    public String getDEV_NUMBER() {
        return DEV_NUMBER;
    }

    public void setDEV_NUMBER(String dEV_NUMBER) {
        DEV_NUMBER = dEV_NUMBER;
    }

    public String getDEV_STATUS() {
        return DEV_STATUS;
    }

    public void setDEV_STATUS(String dEV_STATUS) {
        DEV_STATUS = dEV_STATUS;
    }

    public String getUPLOAD_TIME() {
        return UPLOAD_TIME;
    }

    public void setUPLOAD_TIME(String uPLOAD_TIME) {
        UPLOAD_TIME = uPLOAD_TIME;
    }

    public UploadDeviceStatusData(){

    }

    public UploadDeviceStatusData(String dEV_NUMBER, String dEV_STATUS, String uPLOAD_TIME) {
        DEV_NUMBER = dEV_NUMBER;
        DEV_STATUS = dEV_STATUS;
        UPLOAD_TIME = uPLOAD_TIME;
    }

    @Override
    public String toString() {
        return "UploadDeviceStatusData [DEV_NUMBER=" + DEV_NUMBER + ", DEV_STATUS=" + DEV_STATUS + ", UPLOAD_TIME="
                + UPLOAD_TIME + "]";
    }
}
