package com.example.gongan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.gongan.pojo.UserPram.UploadDeviceInfo;
import com.example.gongan.pojo.UserPram.UploadVehicleInfo;
import com.example.gongan.pojo.UserPram.UserDesensitizationInfo;
import com.example.gongan.pojo.UserPram.UserParm;

import com.example.gongan.restapi.ApiZhsqSdkClient;
import com.example.gongan.util.Bas64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "api")
@Controller
@RequestMapping("/api/gongan")
    public class gonganController {

        @Autowired
        private HttpServletRequest request;

        @Autowired
        private HttpServletResponse response;
        //注入公安部接口
        @Autowired
        private ApiZhsqSdkClient apiHttpClient;

            //0 上传设备信息
            @ApiOperation(value = "上传设备信息")
            @ResponseBody
            @RequestMapping(value = "/uploadDeviceInfos",method = RequestMethod.POST)
            public String UploadDeviceInfos(UploadDeviceInfo info) throws Exception {

                try {
                    JSONArray dataInfo = new JSONArray();
                    JSONObject data1 = new JSONObject();
                    //设备名称
                    data1.put("DEV_NAME","001 智慧门禁设备");
                    // 设备类型
                    data1.put("DEV_TYPE","001");
                    // 设备编码
                    data1.put("DEVICE_NUMBER","1101081111111-001-33566442");
                    // 安装地址
                    data1.put("PLACE","北京市丰台区 XX 小区 X 号楼 X 单元");
                    // 经度
                    data1.put("LONGITUDE","116.313000");
                    // 纬度
                    data1.put("LATITUDE","39.972000");
                    // 备注
                    data1.put("MEMO","测试 1");
                    dataInfo.add(data1);
                } catch (Exception e) {
                    //TODO: handle exception
                }

                
                return "";
            }

            

            //3.2.2上传居民信息
            @ResponseBody
            @RequestMapping(value ="/uploadResidentInfo", headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            @ApiOperation(value = "上传居民信息")
            public String uploadResidentInfo(UserParm userParm,@RequestParam("IDCARD_FACES") MultipartFile multipartFile) throws Exception {
                UserParm userinfo = new UserParm();
                String result;
                String originalFilename = multipartFile.getOriginalFilename();
                //String newFileName =  originalFilename.substring(originalFilename.lastIndexOf(".") - 1);
                File file = null;
                try {
                    File path = new File(ResourceUtils.getURL("classpath:").getPath());
                    File upload = new File(path.getAbsolutePath(), "static/tmpupload/");
                    if (!upload.exists()) upload.mkdirs();
                    String uploadPath = upload + "\\";
                    file = new File(uploadPath + originalFilename);
                    multipartFile.transferTo(file);

                    // //人员头像base64
                    // userinfo.setIDCARD_FACE(Bas64.ImageToBase64(file.toString()));
                    // //居民姓名
                    // userinfo.setRESIDENT_NAME(request.getParameter("RESIDENT_NAME"));
                    // //国籍代码
                    // userinfo.setCITIZENSHIP_CODE(request.getParameter("CITIZENSHIP_CODE"));
                    // //证件类型
                    // userinfo.setIDCARD_ZJLX(request.getParameter("IDCARD_ZJLX"));
                    // //证件号码
                    // userinfo.setIDCARD_ZJHM(request.getParameter("IDCARD_ZJHM"));
                    // //手机号码
                    // userinfo.setPHONE_NUM(request.getParameter("PHONE_NUM"));
                    // //居住地址
                    // userinfo.setJZD_ADDRESS(request.getParameter("JZD_ADDRESS"));
                    // //调用公安部接口


                    JSONArray dataInfo = new JSONArray();
                    JSONObject data1 = new JSONObject();
                    String base64Str = Bas64.ImageToBase64(file.toString());
                    
                    //居民姓名
                    data1.put("RESIDENT_NAME", request.getParameter("RESIDENT_NAME"));
                    // 人脸图片 base64
                    data1.put("IDCARD_FACE", base64Str);
                    // 国籍代码
                    data1.put("CITIZENSHIP_CODE", request.getParameter("CITIZENSHIP_CODE"));
                    // 证件类型
                    data1.put("IDCARD_ZJLX", request.getParameter("IDCARD_ZJLX"));
                    // 证件号码
                    data1.put("IDCARD_ZJHM", request.getParameter("IDCARD_ZJHM"));
                    // 手机号码
                    data1.put("PHONE_NUM", request.getParameter("PHONE_NUM"));
                    // 居住地址
                    data1.put("JZD_ADDRESS", request.getParameter("JZD_ADDRESS"));
                    dataInfo.add(data1);
                    result = apiHttpClient.puttUploadResidentInfo(dataInfo);

                } catch (Exception e) {
                    return "shanchuanjumin" + e.getMessage();
                } finally {
                    try{
                        //file.delete();
                    } catch (Exception e) {
                        // nothing.
                    }
                }

                return result;
            }

            //3.3.1接收居民脱敏信息
            @ApiOperation(value = "接收居民脱敏信息")
            @ResponseBody
            @RequestMapping(value = "/receiveDesInfoResidents",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String receiveDesInfoResidents(UserDesensitizationInfo userDesensitizationInfo) throws Exception {
                UserDesensitizationInfo date_info = new UserDesensitizationInfo();
                String result;
                try {
                    date_info.setStartTime(request.getParameter("startTime"));
                    date_info.setEndTime(request.getParameter("endTime"));
                    date_info.setRows(request.getParameter("rows"));
                    result = apiHttpClient.getReceiveDesInfoResidents(date_info);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "receiveDesInfoResidents error." + e;
                }
                return result;
            }

            //3.2.3 上传车辆信息
            @ApiOperation(value = "上传车辆信息")
            @ResponseBody
            @RequestMapping(value = "/uploadVehicleInfo",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String uploadVehicleInfo(UploadVehicleInfo uploadVehicleInfo) throws Exception {
                UploadVehicleInfo date_info = new UploadVehicleInfo();
                String result;
                try {

                    JSONArray dataInfo = new JSONArray();
                    JSONObject data1 = new JSONObject();
                    // 车牌号码
                    data1.put("VEHICLE_CODE",uploadVehicleInfo.getVEHICLE_CODE());
                    // 使用人姓名
                    data1.put("USER_NAME",uploadVehicleInfo.getUSER_NAME());
                    // 使用人证件类型
                    data1.put("USER_ZJLX",uploadVehicleInfo.getUSER_ZJLX());
                    // 使用人证件号码
                    data1.put("USER_ZJHM",uploadVehicleInfo.getUSER_ZJHM());
                    // 使用人住址
                    data1.put("USER_ADDRESS",uploadVehicleInfo.getUSER_ADDRESS());
                    // 车辆品牌
                    data1.put("VEHICLE_BRAND",uploadVehicleInfo.getVEHICLE_BRAND());
                    // 车牌颜色
                    data1.put("VEHICLE_COLOR",uploadVehicleInfo.getVEHICLE_COLOR());
                    // 备注
                    data1.put("MEMO",uploadVehicleInfo.getMEMO());
                    dataInfo.add(data1);
                    result = apiHttpClient.puttUploadVehicleInfo(dataInfo);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "ss error." + e;
                }
                
                return result;
            }
            
            //4上传车辆识别数据
            @ApiOperation(value = "上传车辆识别数据")
            @ResponseBody
            @RequestMapping(value = "/shangchuancheliang",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String shangchuancheliang(UploadVehicleInfo uploadVehicleInfo) throws Exception {
                UploadVehicleInfo date_info = new UploadVehicleInfo();
                String result;
                try {
                    //车牌号码
                    date_info.setVEHICLE_CODE(request.getParameter("VEHICLE_CODE"));
                    //使用人姓名
                    date_info.setUSER_NAME(request.getParameter("USER_NAME"));
                    //使用人证件类型
                    date_info.setUSER_ZJLX(request.getParameter("USER_ZJLX"));
                    //使用人证件号码
                    date_info.setUSER_ZJHM(request.getParameter("USER_ZJHM"));
                    //使用人居住地址信息
                    date_info.setUSER_ADDRESS(request.getParameter("USER_ADDRESS"));
                    //车辆品牌
                    date_info.setVEHICLE_BRAND(request.getParameter("VEHICLE_BRAND"));
                    //车辆颜色
                    date_info.setVEHICLE_COLOR(request.getParameter("VEHICLE_COLOR"));
                    //备注
                    date_info.setMEMO(request.getParameter("MEMO"));
                    result = apiHttpClient.putCheLiang(date_info);
                    
                } catch (Exception e) {
                    //TODO: handle exception
                    return "uploadVehicleInfo error." + e;
                }

                return result;
            }


}
