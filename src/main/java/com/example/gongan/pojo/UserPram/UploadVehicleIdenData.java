package com.example.gongan.pojo.UserPram;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/23 22:46
 */

@ApiModel("上传车辆信息")
public class UploadVehicleIdenData implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "* 车牌号")
    private String PLATE_NUMBER;
    @ApiModelProperty(value = "* 车辆行驶方向 1-入口, 2-出口")
    private int IN_OUT_TYPE;
    @ApiModelProperty(value = "*车牌照片base64", hidden = true)
    private String IMAGE;
    @ApiModelProperty(value = "* 背景图base64", hidden = true)
    private String BACKGROUND_IMAGE;
    @ApiModelProperty(value = "* 抓拍时间")
    private String CREATETIME;
    @ApiModelProperty(value = "* 设备编码")
    private String DEVICE_NUMBER;
    @ApiModelProperty(value = "* 车牌照片;背景图")
    private MultipartFile[] FACE_IMAGES;

    public String getPLATE_NUMBER() {
        return PLATE_NUMBER;
    }

    public void setPLATE_NUMBER(String pLATE_NUMBER) {
        PLATE_NUMBER = pLATE_NUMBER;
    }

    public int getIN_OUT_TYPE() {
        return IN_OUT_TYPE;
    }

    public void setIN_OUT_TYPE(int iN_OUT_TYPE) {
        IN_OUT_TYPE = iN_OUT_TYPE;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String iMAGE) {
        IMAGE = iMAGE;
    }

    public String getBACKGROUND_IMAGE() {
        return BACKGROUND_IMAGE;
    }

    public void setBACKGROUND_IMAGE(String bACKGROUND_IMAGE) {
        BACKGROUND_IMAGE = bACKGROUND_IMAGE;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String cREATETIME) {
        CREATETIME = cREATETIME;
    }

    public String getDEVICE_NUMBER() {
        return DEVICE_NUMBER;
    }

    public void setDEVICE_NUMBER(String dEVICE_NUMBER) {
        DEVICE_NUMBER = dEVICE_NUMBER;
    }

    public MultipartFile[] getFACE_IMAGES() {
        return FACE_IMAGES;
    }

    public void setFACE_IMAGES(MultipartFile[] fACE_IMAGES) {
        FACE_IMAGES = fACE_IMAGES;
    }

    public UploadVehicleIdenData() {

    }

    public UploadVehicleIdenData(String pLATE_NUMBER, int iN_OUT_TYPE, String iMAGE, String bACKGROUND_IMAGE,
            String cREATETIME, String dEVICE_NUMBER, MultipartFile[] fACE_IMAGES) {
        PLATE_NUMBER = pLATE_NUMBER;
        IN_OUT_TYPE = iN_OUT_TYPE;
        IMAGE = iMAGE;
        BACKGROUND_IMAGE = bACKGROUND_IMAGE;
        CREATETIME = cREATETIME;
        DEVICE_NUMBER = dEVICE_NUMBER;
        FACE_IMAGES = fACE_IMAGES;
    }

    @Override
    public String toString() {
        return "UploadVehicleIdenData [BACKGROUND_IMAGE=" + BACKGROUND_IMAGE + ", CREATETIME=" + CREATETIME
                + ", DEVICE_NUMBER=" + DEVICE_NUMBER + ", FACE_IMAGES=" + Arrays.toString(FACE_IMAGES) + ", IMAGE="
                + IMAGE + ", IN_OUT_TYPE=" + IN_OUT_TYPE + ", PLATE_NUMBER=" + PLATE_NUMBER + "]";
    }

    
   
}
