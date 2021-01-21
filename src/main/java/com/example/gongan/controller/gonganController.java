package com.example.gongan.controller;

import com.example.gongan.pojo.UserPram.UserDesensitizationInfo;
import com.example.gongan.pojo.UserPram.UserParm;
import com.example.gongan.restapi.ApiClient;
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
        private ApiClient apiHttpClient;

            //0 上传设备信息
            @ApiOperation(value = "上传设备信息")
            @ResponseBody
            @RequestMapping(value = "/shangchuanshebei",method = RequestMethod.POST)
            public String shangchuanshebei(

            ) throws Exception {

                apiHttpClient.getUser();
                return "";
            }

            //1上传居民信息
            @ResponseBody
            @RequestMapping(value ="/shanchuanjumin", headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            @ApiOperation(value = "上传居民信息")
            public String shanchuanjumin(UserParm userParm,@RequestParam("IDCARD_FACES") MultipartFile multipartFile) throws Exception {
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

                    //人员头像base64
                    userinfo.setIDCARD_FACE(Bas64.ImageToBase64(file.toString()));
                    //居民姓名
                    userinfo.setRESIDENT_NAME(request.getParameter("RESIDENT_NAME"));
                    //国籍代码
                    userinfo.setCITIZENSHIP_CODE(request.getParameter("CITIZENSHIP_CODE"));
                    //证件类型
                    userinfo.setIDCARD_ZJLX(request.getParameter("IDCARD_ZJLX"));
                    //证件号码
                    userinfo.setIDCARD_ZJHM(request.getParameter("IDCARD_ZJHM"));
                    //手机号码
                    userinfo.setPHONE_NUM(request.getParameter("PHONE_NUM"));
                    //居住地址
                    userinfo.setJZD_ADDRESS(request.getParameter("JZD_ADDRESS"));
                    //调用公安部接口
                    result = apiHttpClient.puttUser(userinfo);

                } catch (Exception e) {
                    return "file upload error.";
                } finally {
                    try{
                        //file.delete();
                    } catch (Exception e) {
                        // nothing.
                    }
                }

                return result;
            }

            //2接收居民脱敏信息
            @ApiOperation(value = "接收居民脱敏信息")
            @ResponseBody
            @RequestMapping(value = "/jieshoujumin",headers = "content-type=multipart/form-data",produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
            public String jieshoujumin(UserDesensitizationInfo userDesensitizationInfo) throws Exception {
                UserDesensitizationInfo date_info = new UserDesensitizationInfo();
                String result;
                try {
                    date_info.setStartTime(request.getParameter("startTime"));
                    date_info.setEndTime(request.getParameter("endTime"));
                    date_info.setRows(request.getParameter("rows"));
                    result = apiHttpClient.getUser(date_info);
                } catch (Exception e) {
                    //TODO: handle exception
                    return "userDesensitizationInfo error." + e;
                }
                return result;
            }

            //3上传人脸识别数据
            @ApiOperation(value = "上传人脸识别数据")
            @ResponseBody
            @RequestMapping(value = "/shangchuanrenlian",method = RequestMethod.POST)
            public String shangchuanrenlian(

            ) throws Exception {
                apiHttpClient.puttUserRenLian();
                return "";
            }
            
            //4上传车辆识别数据
            @ApiOperation(value = "上传车辆识别数据")
            @ResponseBody
            @RequestMapping(value = "/shangchuancheliang",method = RequestMethod.POST)
            public String shangchuancheliang(

            ) throws Exception {
                apiHttpClient.putCheLiang();
                return "";
            }


            //5上传智慧门禁数据
            @ApiOperation(value = "上传智慧门禁数据")
            @ResponseBody
            @RequestMapping(value = "/shangchuanmenjinshuju",method = RequestMethod.POST)
            public String shangchuanmenjinshuju(

            ) throws Exception {
                apiHttpClient.putMenJin();
                return "";
            }
            //6上传设备状态数据
            @ApiOperation(value = "上传设备状态数据")
            @ResponseBody
            @RequestMapping(value = "/shangchuanshebeizhuangtai" ,method = RequestMethod.POST)
            public String shangchuanshebeizhuangtai(

            ) throws Exception {
                apiHttpClient.putSheBei();
                return "";
            }

}
