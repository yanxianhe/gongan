package com.example.gongan.restapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.gongan.pojo.DataItems;
import com.example.gongan.pojo.Dataset;
import com.example.gongan.pojo.RequestParam;
import com.example.gongan.pojo.UserPram.UploadVehicleInfo;
import com.example.gongan.pojo.UserPram.UserDesensitizationInfo;
import com.example.gongan.pojo.UserPram.UploadResidentInfo;
import com.example.gongan.util.Bas64;
import com.example.gongan.util.constant;
import com.zhsq.zhsq.ZhsqClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *对接公安一部接口
 */
@Component
public class ApiZhsqSdkClient {
    @Autowired
    public RestTemplate restTemplate;


    /**
     * 选测项
     * <p> 上传设备信息数据 </p>
     * 
    */
    public String puttUploadDeviceInfos(JSONArray dataInfo) throws Exception {
        JSONObject object;
        String url = constant.gongan_url;
        try {
            ZhsqClient client = new ZhsqClient(url);
            object = client.staticDataUpload(constant.gongan_accessKey, "vehicle", dataInfo.toString());  
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();
    }
    /**
     * 选测项
     * 上传居民信息 已经调通
    */
    public String puttUploadResidentInfo(JSONArray dataInfo) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey",constant.gongan_accessKey);
        //数据类型
        map.add("DataType", constant.data_type);
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            object = client.staticDataUpload(accessKey, "resident", dataInfo.toString());
        } catch (Exception e) {
            return e.getMessage();
        }
        return object.toString();
    }

    /**
     * 选测项
     * 上传车辆信息 已经调通
    */
    public String puttUploadVehicleInfo(JSONArray dataInfo) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey",constant.gongan_accessKey);
        //数据类型
        map.add("DataType", constant.data_type);

        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            object = client.staticDataUpload(constant.gongan_accessKey, "vehicle", dataInfo.toString());  
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();
    }

    /**
     * 必测项
     * 接收脱敏居民信息 已经调通
    */
    public String getReceiveDesInfoResidents(UserDesensitizationInfo userDesensitizationInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            String startTime = userDesensitizationInfo.getStartTime();
            String endTime = userDesensitizationInfo.getEndTime();
            
            object = client.getResData(accessKey,"residentdata",startTime,endTime);
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
    /**
     * 必测项
     * 上传人脸识别数据
    */
    public String putUploadFaceRecognitions(JSONObject dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            object = client.dataUpload(accessKey, dataInfo.toJSONString());
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
    /**
     * 必测项
     * 上传车辆识别数据
    */
    public String putUploadVehicleIdenData(JSONObject dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            object = client.dataUpload(accessKey, dataInfo.toJSONString());
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
    /**
     * 必测项
     * 上传智慧门禁数据
    */
    public String putupSACDatas(JSONObject dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            object = client.dataUpload(accessKey, dataInfo.toJSONString());
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
    /**
     * 必测项
     * 上传设备状态数据
    */
    public String putUploadDeviceStatusDatas(JSONObject dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            object = client.dataUpload(accessKey, dataInfo.toJSONString());
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
    
}
