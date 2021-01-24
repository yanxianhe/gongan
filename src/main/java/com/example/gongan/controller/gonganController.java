package com.example.gongan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.gongan.pojo.UserPram.ReceiveFaceRecognitionData;
import com.example.gongan.pojo.UserPram.ReceiveIntelligentAccessControlData;
import com.example.gongan.pojo.UserPram.ReceiveVehicleDdentificationData;
import com.example.gongan.pojo.UserPram.UploadDeviceInfo;
import com.example.gongan.pojo.UserPram.UploadDeviceStatusData;
import com.example.gongan.pojo.UserPram.UploadFaceRecognition;
import com.example.gongan.pojo.UserPram.UploadVehicleInfo;
import com.example.gongan.pojo.UserPram.UserDesensitizationInfo;
import com.example.gongan.pojo.UserPram.UploadResidentInfo;
import com.example.gongan.pojo.UserPram.UploadSmartAccessControlData;
import com.example.gongan.pojo.UserPram.UploadVehicleIdenData;
import com.example.gongan.restapi.ApiZhsqSdkClient;
import com.example.gongan.util.Bas64;
import com.example.gongan.util.constant;
import com.example.gongan.util.utiltools;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

            /**
            * 3.2.1.3.1 接口介绍
            * 上传设备信息
            */
            @ApiOperation(value = "上传设备信息")
            @ResponseBody
            @RequestMapping(value = "/uploadDeviceInfos",method = RequestMethod.POST)
            public String UploadDeviceInfos(UploadDeviceInfo info) throws Exception {
                String result;

                try {
                    JSONArray dataInfo = new JSONArray();
                    JSONObject data1 = new JSONObject();
                    //设备名称
                    data1.put("DEV_NAME",info.getDEV_NAME());
                    // 设备类型
                    data1.put("DEV_TYPE",info.getDEV_TYPE());
                    // 设备编码
                    data1.put("DEVICE_NUMBER",info.getDEVICE_NUMBER());
                    // 安装地址
                    data1.put("PLACE",info.getPLACE());
                    // 经度
                    data1.put("LONGITUDE",info.getLONGITUDE());
                    // 纬度
                    data1.put("LATITUDE",info.getLATITUDE());
                    // 备注
                    data1.put("MEMO",info.getMEMO());
                    dataInfo.add(data1);

                    result = apiHttpClient.puttUploadDeviceInfos(dataInfo);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "uploadDeviceInfos" + e.getMessage();
                }
                return result;
            }

            /**
             * 3.2.2.3.1 接口介绍
             * 上传居民信息数据
             */
            @ResponseBody
            @RequestMapping(value ="/uploadResidentInfos", headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            @ApiOperation(value = "上传居民信息")
            public String uploadResidentInfos(UploadResidentInfo userParm,@RequestParam("IDCARD_FACES") MultipartFile multipartFile) throws Exception {
                
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

                    JSONArray dataInfo = new JSONArray();
                    JSONObject data1 = new JSONObject();
                    String base64Str = Bas64.ImageToBase64(file.toString());
                    //居民姓名
                    data1.put("RESIDENT_NAME", userParm.getRESIDENT_NAME());
                    // 人脸图片 base64
                    data1.put("IDCARD_FACE", base64Str);
                    // 国籍代码
                    data1.put("CITIZENSHIP_CODE", userParm.getCITIZENSHIP_CODE());
                    // 证件类型
                    data1.put("IDCARD_ZJLX", userParm.getIDCARD_ZJLX());
                    // 证件号码
                    data1.put("IDCARD_ZJHM", userParm.getIDCARD_ZJHM());
                    // 手机号码
                    data1.put("PHONE_NUM", userParm.getPHONE_NUM());
                    // 居住地址
                    data1.put("JZD_ADDRESS", userParm.getJZD_ADDRESS());
                    dataInfo.add(data1);
                    result = apiHttpClient.puttUploadResidentInfo(dataInfo);

                } catch (Exception e) {
                    return "uploadResidentInfo" + e.getMessage();
                } finally {
                    try{
                        //file.delete();
                    } catch (Exception e) {
                        // nothing.
                    }
                }

                return result;
            }
            /**
             * 3.2.3 上传车辆信息
             * 上传居民信息数据
             */
            @ApiOperation(value = "上传车辆信息")
            @ResponseBody
            @RequestMapping(value = "/uploadVehicleInfo",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String uploadVehicleInfo(UploadVehicleInfo uploadVehicleInfo) throws Exception {
                
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

            /**
             * 3.3.1.1 接口介绍
             * 接收居民脱敏信息
            */
            @ApiOperation(value = "接收脱敏居民信息")
            @ResponseBody
            @RequestMapping(value = "/receiveDesInfoResidents",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String receiveDesInfoResidents(UserDesensitizationInfo userDesensitizationInfo) throws Exception {
                
                String result;
                try {
                    result = apiHttpClient.getReceiveDesInfoResidents(userDesensitizationInfo);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "receiveDesInfoResidents error." + e;
                }
                return result;
            }

            /**
             * 3.4.1.1 接口介绍
             * 用来上报人脸识别设备产生的人像抓拍数据
             * */
            @ApiOperation(value = "上传人脸识别数据")
            @ResponseBody
            @RequestMapping(value = "/uploadFaceRecognition",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String uploadFaceRecognitions(UploadFaceRecognition uploadFaceRecognition,@RequestParam MultipartFile[] FACE_IMAGES) throws Exception {
                
                String result = "";
                File filepath = null;
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                File upload = new File(path.getAbsolutePath(), "static/tmpupload/");
                String uploadPath = upload + "\\";
                
                if (FACE_IMAGES != null && FACE_IMAGES.length > 0) {
                    //循环获取file数组中得文件
                    for (int i = 0; i < FACE_IMAGES.length; i++) {
                        MultipartFile file = FACE_IMAGES[i];
                        if (!upload.exists()) upload.mkdirs();
                        filepath = new File(uploadPath + file.getOriginalFilename().toString());
                        
                        file.transferTo(filepath);
                        //0 人脸图片; 1 背景图片
                        if(0 == i){
                            uploadFaceRecognition.setFACE_IMAGE(Bas64.ImageToBase64(filepath.toString()));
                        }else{
                            uploadFaceRecognition.setBACKGROUND_IMAGE(Bas64.ImageToBase64(filepath.toString()));
                        }
                    }
                }

                try {

                    JSONObject json = new JSONObject();
                    JSONArray dataInfo = new JSONArray();
                    List<String> data1 = new ArrayList<String>();
                    // 设备编码
                    data1.add(uploadFaceRecognition.getDEVICE_NUMBER());
                    // 人脸图片
                    data1.add(uploadFaceRecognition.getFACE_IMAGE());
                    // 背景图片
                    data1.add(uploadFaceRecognition.getBACKGROUND_IMAGE());
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 抓拍时间
                    data1.add(format.format(date));
                    dataInfo.add(data1);
                    
                    List<String> columns = new ArrayList<>();
                    columns.add("DEVICE_NUMBER");
                    columns.add("FACE_IMAGE");
                    columns.add("BACKGROUND_IMAGE");
                    columns.add("CAPTURE_TIME ");
                    
                    JSONArray dataItems = new JSONArray();
                    for (int i = 0; i < columns.size(); i++) {
                        JSONObject item = new JSONObject();
                        item.put("Name", columns.get(i));
                        item.put("Type", "String");
                        item.put("Fmt", "");
                        dataItems.add(item);
                    }
                    JSONObject dataset = new JSONObject();
                    dataset.put("ResourceName", "capturedata");
                    dataset.put("DataItems", dataItems);
                    dataset.put("DataInfo", dataInfo);

                    // From:数据来源端-小区编码
                    json.put("From", constant.area_code);
                    // TO:服务端-平台
                    json.put("To", "110000000001");
                    // MessageSequence:消息的唯一标识-uuid
                    json.put("MessageSequence", utiltools.getDateUUid(5));
                    JSONObject requestParam = new JSONObject();
                    // isTransaction:是否开启事务-默认 0
                    requestParam.put("isTransaction", "0");
                    JSONArray datasets = new JSONArray();
                    // 获取上传的数据-门禁：getTrafficData，车闸：getVehicleData，人像：getImageCapture
                    datasets.add(dataset);
                    // 消息体添加数据
                    requestParam.put("Dataset", datasets);
                    json.put("RequestParam", requestParam);
                    result = apiHttpClient.putUploadFaceRecognitions(json);
                    
                } catch (Exception e) {
                    //TODO: handle exception
                    return "receiveDesInfoResidents error." + e;
                }
                return result;
            }
            /**
             * 3.4.2.1 接口介绍
             * 上传车辆识别数据
            */

            @ApiOperation(value = "上传车辆识别数据")
            @ResponseBody
            @RequestMapping(value = "/uploadVehicleIdenDatas",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String uploadVehicleIdenDatas(UploadVehicleIdenData uploadVehicleIdenData,@RequestParam MultipartFile[] FACE_IMAGES) throws Exception {
                
                String result = "";
                try {
                    
                    File filepath = null;
                    File path = new File(ResourceUtils.getURL("classpath:").getPath());
                    File upload = new File(path.getAbsolutePath(), "static/tmpupload/");
                    String uploadPath = upload + "\\";
                    
                    if (FACE_IMAGES != null && FACE_IMAGES.length > 0) {
                        //循环获取file数组中得文件
                        for (int i = 0; i < FACE_IMAGES.length; i++) {
                            MultipartFile file = FACE_IMAGES[i];
                            if (!upload.exists()) upload.mkdirs();
                            filepath = new File(uploadPath + file.getOriginalFilename().toString());
                            
                            file.transferTo(filepath);
                            //0 车牌照片; 1 背景图片
                            if(0 == i){
                                uploadVehicleIdenData.setIMAGE(Bas64.ImageToBase64(filepath.toString()));
                            }else{
                                uploadVehicleIdenData.setBACKGROUND_IMAGE(Bas64.ImageToBase64(filepath.toString()));
                            }
                        }
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                    return "uploadVehicleIdenDatas error." + e.getMessage();
                }


                try {
                    JSONArray dataInfo = new JSONArray();
                    List<String> data1 = new ArrayList<String>();
                    // 车牌信息
                    data1.add(uploadVehicleIdenData.getPLATE_NUMBER());
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 设备编码
                    data1.add(uploadVehicleIdenData.getDEVICE_NUMBER());
                    // 进出类型
                    data1.add(String.valueOf(uploadVehicleIdenData.getIN_OUT_TYPE()));
                    // 车牌图片 base64
                    data1.add(uploadVehicleIdenData.getIMAGE());
                    // 背景图片 base64
                    data1.add(uploadVehicleIdenData.getBACKGROUND_IMAGE());
                    // 创建时间
                    data1.add(format.format(date));
                    dataInfo.add(data1);

                    List<String> columns = new ArrayList<>();
                    columns.add("PLATE_NUMBER");
                    columns.add("DEVICE_NUMBER");
                    columns.add("IN_OUT_TYPE");
                    columns.add("IMAGE");
                    columns.add("BACKGROUND_IMAGE");
                    columns.add("CREATETIME");

                    JSONArray dataItems = new JSONArray();
                    for (int i = 0; i < columns.size(); i++) {
                        JSONObject item = new JSONObject();
                        item.put("Name", columns.get(i));
                        item.put("Type", "String");
                        item.put("Fmt", "");
                        dataItems.add(item);
                    }
                    JSONObject dataset = new JSONObject();
                    dataset.put("ResourceName", "vehicledata");
                    dataset.put("DataItems", dataItems);
                    dataset.put("DataInfo", dataInfo);

                    JSONObject json = new JSONObject();
                    // From:数据来源端-小区编码
                    json.put("From", constant.area_code);
                    // TO:服务端-平台
                    json.put("To", "110000000001");
                    // MessageSequence:消息的唯一标识-uuid
                    json.put("MessageSequence", utiltools.getDateUUid(5));
                    JSONObject requestParam = new JSONObject();
                    // isTransaction:是否开启事务-默认 0
                    requestParam.put("isTransaction", "0");
                    JSONArray datasets = new JSONArray();
                    // 获取上传的数据-门禁：getTrafficData，车闸：getVehicleData，人像：getImageCapture
                    datasets.add(dataset);
                    // 消息体添加数据
                    requestParam.put("Dataset", datasets);
                    json.put("RequestParam", requestParam);

                    result = apiHttpClient.putUploadVehicleIdenData(json);
                    
                } catch (Exception e) {
                    //TODO: handle exception
                    return "uploadVehicleIdenDatas error." + e.getMessage();
                }

                return result;
            }

            /**
             * 3.2.2.3.1 接口介绍
             * 上传智慧门禁数据
             */
            @ResponseBody
            @RequestMapping(value ="/upSACDatas", headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            @ApiOperation(value = "上传智慧门禁数据")
            public String upSACDatas(UploadSmartAccessControlData upSACData,@RequestParam("FACE_IMAGES") MultipartFile multipartFile) throws Exception {
                
                String result;
                try {
                    File file = null;
                    String originalFilename = multipartFile.getOriginalFilename();
                    File path = new File(ResourceUtils.getURL("classpath:").getPath());
                    File upload = new File(path.getAbsolutePath(), "static/tmpupload/");
                    if (!upload.exists()) upload.mkdirs();
                    String uploadPath = upload + "\\";
                    file = new File(uploadPath + originalFilename);
                    multipartFile.transferTo(file);
                    upSACData.setFACE(Bas64.ImageToBase64(file.toString()));
                    
                } catch (Exception e) {
                    return "upSACDatas" + e.getMessage() ;
                }
                try {
                    JSONArray dataInfo = new JSONArray();
                    // 上传的数据内容-与字段一一对应
                    List<String> data1 = new ArrayList<String>();
                    // 居民信息 id
                    data1.add(upSACData.getRESIDENT_ID());
                    // 图片信息的 base64 串
                    data1.add(upSACData.getFACE());
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 创建时间
                    data1.add(format.format(date));
                    // 开门方式
                    data1.add(upSACData.getOPENTYPE());
                    // 设备编码
                    data1.add(upSACData.getDEVICE_NUMBER());
                    // 开门状态
                    data1.add(upSACData.getOPEN_STATUS());
                    dataInfo.add(data1);

                    // 上传的字段
                    List<String> columns = new ArrayList<>();
                    columns.add("RESIDENT_ID");
                    columns.add("FACE");
                    columns.add("CREATETIME");
                    columns.add("OPENTYPE");
                    columns.add("DEVICE_NUMBER");
                    columns.add("OPEN_STATUS");

                    JSONArray dataItems = new JSONArray();
                    // 构造字段
                    for (int i = 0; i < columns.size(); i++) {
                        JSONObject item = new JSONObject();
                        item.put("Name", columns.get(i));
                        item.put("Type", "String");
                        item.put("Fmt", "");
                        dataItems.add(item);
                    }
                    JSONObject requestParam = new JSONObject();
                    JSONObject dataset = new JSONObject();
                    JSONArray datasets = new JSONArray();
                    JSONObject json = new JSONObject();
                    // ResourceName：数据类别的标识
                    dataset.put("ResourceName", "trafficdata");

                    // DataItems：数据字段
                    dataset.put("DataItems", dataItems);
                    // DataInfo：数据内容
                    dataset.put("DataInfo", dataInfo);

                    datasets.add(dataset);
                    // 消息体添加数据
                    requestParam.put("Dataset", datasets);
                    json.put("RequestParam", requestParam);

                    result = apiHttpClient.putupSACDatas(json);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "upSACDatas" + e.getMessage();
                }
                return result;
            }
            /**
             * 3.4.4.1 接口介绍
             * 上传设备状态数据
            */
            @ApiOperation(value = "上传设备状态数据")
            @ResponseBody
            @RequestMapping(value = "/uploadDeviceStatusDatas",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String uploadDeviceStatusDatas(UploadDeviceStatusData uploadDeviceStatusData) throws Exception {
                
                String result;
                try {
                    JSONObject json = new JSONObject();
                    JSONArray dataInfo = new JSONArray();
                    JSONObject requestParam = new JSONObject();
                    JSONArray datasets = new JSONArray();

                    List<String> data1 = new ArrayList<String>();
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 设备编码
                    data1.add(uploadDeviceStatusData.getDEV_NUMBER());
                    // 设备状态
                    data1.add(uploadDeviceStatusData.getDEV_STATUS());
                    String UPLOAD_TIME = uploadDeviceStatusData.getUPLOAD_TIME();
                    // 上传时间
                    if(UPLOAD_TIME.isEmpty()){
                        
                        data1.add(format.format(date));
                    }else{
                        data1.add(format.format(UPLOAD_TIME));
                    }
                    

                    dataInfo.add(data1);
                    
                    List<String> columns = new ArrayList<>();
                    columns.add("DEV_NUMBER");
                    columns.add("DEV_STATUS");
                    columns.add("UPLOAD_TIME");
                    
                    JSONArray dataItems = new JSONArray();
                    for (int i = 0; i < columns.size(); i++) {
                    JSONObject item = new JSONObject();
                        item.put("Name", columns.get(i));
                        item.put("Type", "String");
                        item.put("Fmt", "");
                        dataItems.add(item);
                    }
                    JSONObject dataset = new JSONObject();
                    dataset.put("ResourceName", "devdata");
                    dataset.put("DataItems", dataItems);
                    dataset.put("DataInfo", dataInfo);
                    datasets.add(dataset);
                    // 消息体添加数据
                    requestParam.put("Dataset", datasets);
                    json.put("RequestParam", requestParam);
                    result = apiHttpClient.putUploadDeviceStatusDatas(json);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "uploadDeviceStatusDatas error." + e;
                }
                return result;
            }

            /**
             * 3.4.4.1 接口介绍
             * 接收人脸识别数据
            */
            @ApiOperation(value = "接收人脸识别数据")
            @ResponseBody
            @RequestMapping(value = "/receiveFaceRecognitionDatas",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String receiveFaceRecognitionDatas(ReceiveFaceRecognitionData receiveFaceRecognitionData) throws Exception {
                String result;
                try {
                    result = apiHttpClient.getReceiveFaceRecognitionDatas(receiveFaceRecognitionData);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "receiveDesInfoResidents error." + e;
                }
                return result;
            }
            /**
             * 3.5.2.1 接口介绍
             * 接收车辆识别数据
            */
            @ApiOperation(value = "接收车辆识别数据")
            @ResponseBody
            @RequestMapping(value = "/receiveVehicleDdentificationDatas",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String receiveVehicleDdentificationDatas(ReceiveVehicleDdentificationData receiveVehicleDdentificationData) throws Exception {
                
                String result;
                try {
                    result = apiHttpClient.getReceiveVehicleDdentificationDatas(receiveVehicleDdentificationData);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "receiveDesInfoResidents error." + e;
                }
                return result;
            }
            
            /**
             * 3.5.3.1 接口介绍
             * 接收智慧门禁数据
            */
            @ApiOperation(value = "接收智慧门禁数据")
            @ResponseBody
            @RequestMapping(value = "/receiveIntelligentAccessControlDatas",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String receiveIntelligentAccessControlDatas(ReceiveIntelligentAccessControlData receiveIntelligentAccessControlData) throws Exception {
                String result;
                try {
                    result = apiHttpClient.getReceiveIntelligentAccessControlDatas(receiveIntelligentAccessControlData);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "receiveDesInfoResidents error." + e;
                }
                return result;
            }
}
