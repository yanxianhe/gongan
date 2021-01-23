package com.example.gongan.restapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.gongan.pojo.DataItems;
import com.example.gongan.pojo.Dataset;
import com.example.gongan.pojo.RequestParam;
import com.example.gongan.pojo.UserPram.UploadVehicleInfo;
import com.example.gongan.pojo.UserPram.UserDesensitizationInfo;
import com.example.gongan.pojo.UserPram.UploadResidentInfo;
import com.example.gongan.restconfig.RestTemplateConfig;
import com.example.gongan.util.Bas64;
import com.example.gongan.util.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.swagger.models.HttpMethod;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 *对接公安一部接口
 */
@Component
public class ApiClient {
    @Autowired
    public RestTemplate restTemplate;

    //上传居民信息
    public String puttUser(UploadResidentInfo userinfo) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //String url = constant.gongan_url + "/api/hello/getgongan";
        String url = constant.gongan_url;
        
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey",constant.gongan_accessKey);
        //数据类型
        map.add("DataType", constant.data_type);
        //传入居民对象
        map.add("RequestParam",userinfo);
        //发送请求
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String body = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        return body;
    }
    //获得脱敏信息
    public String getUser(UserDesensitizationInfo date_info) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //String url = "https://172.31.35.20:99/api/getdata";
        String url = constant.gongan_url + "/api/hello/getdata";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
       // Map<String, Object> uriVariables = new HashMap<String, Object>();
        map.add("AccessKey", "F209F3288DD39E28FD398B1048643CDD");
        map.add("dataType", "residentdata");

        map.add("RequestParam",date_info);

        
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String result = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+result);
        return result;

    }
    public String putCheLiang(UploadVehicleInfo date_info) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //String url = "https://172.31.35.20:99/api/getdata";
        String url = constant.gongan_url + "/api/hello/getdata";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
       // Map<String, Object> uriVariables = new HashMap<String, Object>();
        map.add("AccessKey", "F209F3288DD39E28FD398B1048643CDD");
        map.add("dataType", "residentdata");

        map.add("RequestParam",date_info);

        
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String result = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+result);
        return result;

    }
    /**
     * 
     * 上传车辆信息
    */
    public String puttUploadVehicleInfo(UploadVehicleInfo uploadVehicleInfo) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send/data
        //String url = constant.gongan_url + "/api/hello/getdata";
        String url = constant.gongan_url;

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey",constant.gongan_accessKey);
        //数据类型
        map.add("DataType", constant.data_type);
        //主要参数
        map.add("RequestParam",uploadVehicleInfo);

        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        // String result = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        // System.out.println("公安一部回复"+result);


        ResponseEntity<String> result=restTemplate.postForEntity(url,requestBody,String.class);
        
        return result.toString();

    }


    private HttpEntity entity(UploadVehicleInfo uploadVehicleInfo) {
        return null;
    }

    // 一下是动态数据上传
    //上传人脸数据
    public String puttUserRenLian(UploadVehicleInfo uploadVehicleInfo)throws Exception {
        HttpHeaders headers = new HttpHeaders();
        //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send/data
        String url = constant.gongan_url + "/api/hello/data";
        //创建数据源dataItems
        DataItems dataItems = new DataItems();
        dataItems.setName("DEVICE_NUMBER");
        dataItems.setType("");
        dataItems.setFmt("");
        //创建数据源dataset
        Dataset dataset = new Dataset();
        dataset.setDataItems(dataItems);
        dataset.setResourceName("capturedata");
        List<String> data1 = new ArrayList<String>();
        // 设备编码
        data1.add("1101133343222-002-B001");
       // String s1 = Bas64.ImageToBase64("E:\\code\\gongan\\src\\main\\resources\\img\\a.jpg");
        // 人脸图片(需要修改一下)
        data1.add("base64 字符串");
        // 背景图片(需要修改一下)
        data1.add("base64 字符串");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 抓拍时间
        data1.add(format.format(date));
        dataset.setDataInfo(data1);
        //创建主要参数数据
        RequestParam requestParam = new RequestParam();
        requestParam.setDataset(dataset);
        requestParam.setIsTransaction("0");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey","F209F3288DD39E28FD398B1048643CDD");
        //数据唯一标识(需要修改一下)
        map.add("MessageSequence", "2019010714141200001");
        //主要参数
        map.add("RequestParam",requestParam);
        //发送请求
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String result = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+result);
        return result;

    }

    //上传智慧门禁数据
    public void putMenJin()
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send
        String url = constant.gongan_url + "/api/hello/testAllc";
        //创建数据源
        DataItems dataItems = new DataItems();
        dataItems.setName("RESIDENT_ID");
        dataItems.setType("");
        dataItems.setFmt("");
        //创建数据源
        Dataset dataset = new Dataset();
        dataset.setDataItems(dataItems);
        dataset.setResourceName("trafficdata");
        List<String> data1 = new ArrayList<String>();
        // 居民信息 id
        data1.add("21f07e41231757d30b91b1f88ac45e00");
        // 图片信息的 base64 串
        data1.add("图片信息的 base64 串");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 创建时间
        data1.add(format.format(date));
        // 开门方式
        data1.add("01");
        // 设备编码
        data1.add("1101142332445-003-1");
        // 开门状态
        data1.add("001");
        dataset.setDataInfo(data1);
        //创建主要参数数据
        RequestParam requestParam = new RequestParam();
        requestParam.setDataset(dataset);
        requestParam.setIsTransaction("0");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey","F209F3288DD39E28FD398B1048643CDD");
        //数据唯一标识(需要修改一下)
        map.add("MessageSequence", "2019010714141200003");
        //主要参数
        map.add("RequestParam",requestParam);
        //发送请求
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String body = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+body);

    }


    //上传设备状态数据
    public void putSheBei()
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send/data
        String url = constant.gongan_url + "/api/hello/testAlld";
        //创建数据源
        DataItems dataItems = new DataItems();
        dataItems.setName("DEV_NUMBER");
        dataItems.setType("");
        dataItems.setFmt("");
        //创建数据源
        Dataset dataset = new Dataset();
        dataset.setDataItems(dataItems);
        dataset.setResourceName("devdata");
        List<String> data1 = new ArrayList<String>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设备编码
        data1.add("1101080801028-002-3356");
        // 设备状态
        data1.add("1");
        // 上传时间
        data1.add(format.format(date));
        dataset.setDataInfo(data1);
        //创建主要参数数据
        RequestParam requestParam = new RequestParam();
        requestParam.setDataset(dataset);
        requestParam.setIsTransaction("0");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey","F209F3288DD39E28FD398B1048643CDD");
        //数据唯一标识(需要修改一下)
        map.add("MessageSequence", "2019010714141200004");
        //主要参数
        map.add("RequestParam",requestParam);
        //发送请求
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String body = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+body);

    }










}
