package com.example.gongan.restapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.gongan.pojo.UserPram.UserDesensitizationInfo;
import com.example.gongan.pojo.UserPram.ReceiveFaceRecognitionData;
import com.example.gongan.pojo.UserPram.ReceiveIntelligentAccessControlData;
import com.example.gongan.pojo.UserPram.ReceiveVehicleDdentificationData;
import com.example.gongan.util.constant;
import com.zhsq.zhsq.ZhsqClient;
import org.springframework.beans.factory.annotation.Autowired;
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
            object = client.staticDataUpload(constant.gongan_accessKey, "dev", dataInfo.toString());  
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
            if(startTime.isEmpty() || endTime.isEmpty()){
                object = client.getResData(accessKey, "residentdata");
            }else{
                object = client.getResData(accessKey,"residentdata",startTime,endTime);
            }
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


    /**
     * 选测项
     * 接收脱敏居民信息
    */
    public String getReceiveFaceRecognitionDatas(ReceiveFaceRecognitionData dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            String startTime = dataInfo.getStartTime();
            String endTime = dataInfo.getEndTime();
            if(startTime.isEmpty() || endTime.isEmpty()){
                object = client.getResData(accessKey,"capturedata");
            }else{
                object = client.getResData(accessKey,"capturedata",startTime,endTime);
            }
            
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
    
    /**
     * 选测项
     * 接收车辆识别数据
    */
    public String getReceiveVehicleDdentificationDatas(ReceiveVehicleDdentificationData dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            String startTime = dataInfo.getStartTime();
            String endTime = dataInfo.getEndTime();
            if(startTime.isEmpty() || endTime.isEmpty()){
                object = client.getResData(accessKey,"vehicledata");
            }else{
                object = client.getResData(accessKey,"vehicledata",startTime,endTime);
            }
            
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }

    /**
     * 选测项
     * 接收智慧门禁数据
    */
    public String getReceiveIntelligentAccessControlDatas(ReceiveIntelligentAccessControlData dataInfo) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = constant.gongan_url;
        JSONObject object;
        try {
            ZhsqClient client = new ZhsqClient(url);
            String accessKey = constant.gongan_accessKey;
            String startTime = dataInfo.getStartTime();
            String endTime = dataInfo.getEndTime();
            if(startTime.isEmpty() || endTime.isEmpty()){
                object = client.getResData(accessKey,"trafficdata");
            }else{
                object = client.getResData(accessKey,"trafficdata",startTime,endTime);
            }
            
        } catch (Exception e) {
            //TODO: handle exception
            return e.getMessage();
        }
        return object.toString();

    }
}
