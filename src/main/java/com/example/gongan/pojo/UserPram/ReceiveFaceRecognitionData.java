package com.example.gongan.pojo.UserPram;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/24 14:46
 */
@ApiModel("接收人脸识别数据")
public class ReceiveFaceRecognitionData implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "获取结果记录数")
    private String rows;

    
    public ReceiveFaceRecognitionData() {
    }
    public ReceiveFaceRecognitionData(String startTime, String endTime, String rows) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.rows = rows;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    

    @Override
    public String toString() {
        return "DesensitizationInfo [endTime=" + endTime + ", rows=" + rows + ", startTime=" + startTime + "]";
    }
    
}
