package com.example.gongan.restapi;

import com.example.gongan.pojo.DataItems;
import com.example.gongan.pojo.Dataset;
import com.example.gongan.pojo.RequestParam;
import com.example.gongan.pojo.UserPram.UploadResidentInfo;
import com.example.gongan.util.Bas64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 *对接公安一部接口
 */
@Component
public class ApiHttpClient {
    @Autowired
    public RestTemplate restTemplate;

    //上传居民信息
    public void puttUser()
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://192.168.0.101:8082/api/hello/getgongan";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        //访问令牌
        map.add("AccessKey","F209F3288DD39E28FD398B1048643CDD");
        //数据类型
        map.add("DataType", "resident");
        //创建居民对象
        //居民姓名
        //data1.put("RESIDENT_NAME", "测试 1");
        // 人脸图片 base64
        //data1.put("IDCARD_FACE", "base64 字符串");
        // 国籍代码
        //data1.put("CITIZENSHIP_CODE", "CHN");
        // 证件类型
        //data1.put("IDCARD_ZJLX", "111");
       // 证件号码
       // data1.put("IDCARD_ZJHM", "110101199003078793");
       // 手机号码
       // data1.put("PHONE_NUM", "13970453216");
        // 居住地址
        //data1.put("JZD_ADDRESS", "测试小区 11 单元 11 号楼");
        UploadResidentInfo userParm = new UploadResidentInfo();
       // 居民姓名
        userParm.setRESIDENT_NAME("孙尚玉");
        // 人脸图片 base64
        String s = Bas64.ImageToBase64("E:\\code\\gongan\\src\\main\\resources\\img\\a.jpg");
        userParm.setIDCARD_FACE(s);
        // 国籍代码
        userParm.setCITIZENSHIP_CODE("CHN");
        // 证件类型
        userParm.setIDCARD_ZJLX("111");
        //身份证号
        userParm.setIDCARD_ZJHM("110101199003078793");
        // 手机号码
        userParm.setPHONE_NUM("13970453216");
        // 居住地址
        userParm.setJZD_ADDRESS("测试小区 11 单元 11 号楼");
        //传入居民对象
        map.add("RequestParam",userParm);
        //发送请求
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String body = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+body);


    }
    //获得脱敏信息
    public void getUser() throws Exception{
        String url = "https://172.31.35.20:99/api/getdata";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
       // Map<String, Object> uriVariables = new HashMap<String, Object>();
        map.add("AccessKey", "F209F3288DD39E28FD398B1048643CDD");
        map.add("dataType", "residentdata");

        //发送get请求
        String result = restTemplate.getForObject(url, String.class, map);
        System.out.println("公安一部回复"+result);

    }
//一下是动态数据上传
    //上传人脸数据
    public void puttUserRenLian()
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send/data
        String url = "http://192.168.0.101:8082/api/hello/testAlla";
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
        String body = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+body);

    }

    //上传车辆识别信息
    public void putCheLiang()
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
                //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send/data
        String url = "http://192.168.0.101:8082/api/hello/testAllb";
        //创建数据源
        DataItems dataItems = new DataItems();
        dataItems.setName("PLATE_NUMBER");
        dataItems.setType("");
        dataItems.setFmt("");
        //创建数据源
        Dataset dataset = new Dataset();
        dataset.setDataItems(dataItems);
        dataset.setResourceName("vehicledata");
        List<String> data1 = new ArrayList<String>();
        // 车牌信息
        data1.add("粤 A66666");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设备编码
        data1.add("1101142332445-002-33333");
        // 进出类型
        data1.add("1");
        // 获得车牌图片 base64字符串
        String s1 = Bas64.ImageToBase64("E:\\code\\gongan\\src\\main\\resources\\img\\a.jpg");
        // 车牌图片 base64
        data1.add(s1);
        // 背景图片 base64
        data1.add("背景图片 base64");
        // 创建时间
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
        map.add("MessageSequence", "2019010714141200002");
        //主要参数
        map.add("RequestParam",requestParam);
        //发送请求
        HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(map, headers);
        String body = restTemplate.postForEntity(url, requestBody, String.class).getBody();
        System.out.println("公安一部回复"+body);

    }


    //上传智慧门禁数据
    public void putMenJin()
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        //这个自测的时候会报错
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //https://172.31.35.20:99/send
        String url = "http://192.168.0.101:8082/api/hello/testAllc";
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
        String url = "http://192.168.0.101:8082/api/hello/testAlld";
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
