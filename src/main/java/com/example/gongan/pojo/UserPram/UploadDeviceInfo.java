package com.example.gongan.pojo.UserPram;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/22 17:46
 */

@ApiModel("上传设备信息")
public class UploadDeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备名称")
    private String DEV_NAME;
    @ApiModelProperty(value = "设备类型")
    private String DEV_TYPE;
    @ApiModelProperty(value = "设备编码")
    private String DEVICE_NUMBER;
    @ApiModelProperty(value = "安装地址")
    private String PLACE;
    @ApiModelProperty(value = "经度")
    private String LONGITUDE;
    @ApiModelProperty(value = "纬度")
    private String LATITUDE;
    @ApiModelProperty(value = "备注")
    private String MEMO;

	public String getDEV_NAME() {
		return DEV_NAME;
	}
	public void setDEV_NAME(String dEV_NAME) {
		DEV_NAME = dEV_NAME;
	}
	public String getDEV_TYPE() {
		return DEV_TYPE;
	}
	public void setDEV_TYPE(String dEV_TYPE) {
		DEV_TYPE = dEV_TYPE;
	}
	public String getDEVICE_NUMBER() {
		return DEVICE_NUMBER;
	}
	public void setDEVICE_NUMBER(String dEVICE_NUMBER) {
		DEVICE_NUMBER = dEVICE_NUMBER;
	}
	public String getPLACE() {
		return PLACE;
	}
	public void setPLACE(String pLACE) {
		PLACE = pLACE;
	}
	public String getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public String getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}

    public UploadDeviceInfo() {
    }
	public UploadDeviceInfo(String dEV_NAME, String dEV_TYPE, String dEVICE_NUMBER, String pLACE, String lONGITUDE,
			String lATITUDE, String mEMO) {
		DEV_NAME = dEV_NAME;
		DEV_TYPE = dEV_TYPE;
		DEVICE_NUMBER = dEVICE_NUMBER;
		PLACE = pLACE;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
		MEMO = mEMO;
	}
	@Override
	public String toString() {
		return "UploadDeviceInfo [DEVICE_NUMBER=" + DEVICE_NUMBER + ", DEV_NAME=" + DEV_NAME + ", DEV_TYPE=" + DEV_TYPE
				+ ", LATITUDE=" + LATITUDE + ", LONGITUDE=" + LONGITUDE + ", MEMO=" + MEMO + ", PLACE=" + PLACE + "]";
	} 
}
