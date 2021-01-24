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
 * @date 2021/1/22 17:46
 */

@ApiModel("上传的人脸识别")
public class UploadFaceRecognition implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "设备编码")
    private String DEVICE_NUMBER;
    @ApiModelProperty(value = "人脸图片base64", hidden = true)
    private String FACE_IMAGE;
    @ApiModelProperty(value = "背景图片base64", hidden = true)
    private String BACKGROUND_IMAGE;
    @ApiModelProperty(value = "抓拍时间")
    private String CAPTURE_TIME;

    @ApiModelProperty(value = "人脸图片;背景图片")
    private MultipartFile[] FACE_IMAGES;

    public UploadFaceRecognition() {

    }

    public String getDEVICE_NUMBER() {
        return DEVICE_NUMBER;
    }

    public void setDEVICE_NUMBER(String dEVICE_NUMBER) {
        DEVICE_NUMBER = dEVICE_NUMBER;
    }

    public String getFACE_IMAGE() {
        return FACE_IMAGE;
    }

    public void setFACE_IMAGE(String fACE_IMAGE) {
        FACE_IMAGE = fACE_IMAGE;
    }

    public String getBACKGROUND_IMAGE() {
        return BACKGROUND_IMAGE;
    }

    public void setBACKGROUND_IMAGE(String bACKGROUND_IMAGE) {
        BACKGROUND_IMAGE = bACKGROUND_IMAGE;
    }

    public String getCAPTURE_TIME() {
        return CAPTURE_TIME;
    }

    public void setCAPTURE_TIME(String cAPTURE_TIME) {
        CAPTURE_TIME = cAPTURE_TIME;
    }

    public MultipartFile[] getFACE_IMAGES() {
        return FACE_IMAGES;
    }

    public void setFACE_IMAGES(MultipartFile[] fACE_IMAGES) {
        FACE_IMAGES = fACE_IMAGES;
    }

    public UploadFaceRecognition(String dEVICE_NUMBER, String fACE_IMAGE, String bACKGROUND_IMAGE, String cAPTURE_TIME,
            MultipartFile[] fACE_IMAGES) {
        DEVICE_NUMBER = dEVICE_NUMBER;
        FACE_IMAGE = fACE_IMAGE;
        BACKGROUND_IMAGE = bACKGROUND_IMAGE;
        CAPTURE_TIME = cAPTURE_TIME;
        FACE_IMAGES = fACE_IMAGES;
    }

    @Override
    public String toString() {
        return "UploadFaceRecognition [BACKGROUND_IMAGE=" + BACKGROUND_IMAGE + ", CAPTURE_TIME=" + CAPTURE_TIME
                + ", DEVICE_NUMBER=" + DEVICE_NUMBER + ", FACE_IMAGE=" + FACE_IMAGE + ", FACE_IMAGES="
                + Arrays.toString(FACE_IMAGES) + "]";
    }

    
}
