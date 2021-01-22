package com.example.gongan.pojo.UserPram;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/21 22:46
 */

@ApiModel("上传车辆信息")
public class UploadVehicleInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "车牌号码")
    private String VEHICLE_CODE;
    @ApiModelProperty(value = "使用人姓名")
    private String USER_NAME;
    @ApiModelProperty(value = "使用人证件类型")
    private String USER_ZJLX;
    @ApiModelProperty(value = "使用人证件号码")
    private String USER_ZJHM;
    @ApiModelProperty(value = "使用人居住地址信息")
    private String USER_ADDRESS;
    @ApiModelProperty(value = "车辆品牌")
    private String VEHICLE_BRAND;
    @ApiModelProperty(value = "车辆颜色")
    private String VEHICLE_COLOR;
    @ApiModelProperty(value = "备注")
    private String MEMO;

    public String getVEHICLE_CODE() {
        return VEHICLE_CODE;
    }

    public void setVEHICLE_CODE(String vEHICLE_CODE) {
        VEHICLE_CODE = vEHICLE_CODE;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String uSER_NAME) {
        USER_NAME = uSER_NAME;
    }

    public String getUSER_ZJLX() {
        return USER_ZJLX;
    }

    public void setUSER_ZJLX(String uSER_ZJLX) {
        USER_ZJLX = uSER_ZJLX;
    }

    public String getUSER_ZJHM() {
        return USER_ZJHM;
    }

    public void setUSER_ZJHM(String uSER_ZJHM) {
        USER_ZJHM = uSER_ZJHM;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String uSER_ADDRESS) {
        USER_ADDRESS = uSER_ADDRESS;
    }

    public String getVEHICLE_BRAND() {
        return VEHICLE_BRAND;
    }

    public void setVEHICLE_BRAND(String vEHICLE_BRAND) {
        VEHICLE_BRAND = vEHICLE_BRAND;
    }

    public String getVEHICLE_COLOR() {
        return VEHICLE_COLOR;
    }

    public void setVEHICLE_COLOR(String vEHICLE_COLOR) {
        VEHICLE_COLOR = vEHICLE_COLOR;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String mEMO) {
        MEMO = mEMO;
    }
    public UploadVehicleInfo() {
    }

    public UploadVehicleInfo(String vEHICLE_CODE, String uSER_NAME, String uSER_ZJLX, String uSER_ZJHM,
            String uSER_ADDRESS, String vEHICLE_BRAND, String vEHICLE_COLOR, String mEMO) {
        VEHICLE_CODE = vEHICLE_CODE;
        USER_NAME = uSER_NAME;
        USER_ZJLX = uSER_ZJLX;
        USER_ZJHM = uSER_ZJHM;
        USER_ADDRESS = uSER_ADDRESS;
        VEHICLE_BRAND = vEHICLE_BRAND;
        VEHICLE_COLOR = vEHICLE_COLOR;
        MEMO = mEMO;
    }

    @Override
    public String toString() {
        return "UploadVehicleInfo [MEMO=" + MEMO + ", USER_ADDRESS=" + USER_ADDRESS + ", USER_NAME=" + USER_NAME
                + ", USER_ZJHM=" + USER_ZJHM + ", USER_ZJLX=" + USER_ZJLX + ", VEHICLE_BRAND=" + VEHICLE_BRAND
                + ", VEHICLE_CODE=" + VEHICLE_CODE + ", VEHICLE_COLOR=" + VEHICLE_COLOR + "]";
    }

    
    
}
