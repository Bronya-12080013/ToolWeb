package com.lan.interest.controller;


import com.alibaba.fastjson.JSONObject;
import com.lan.interest.aliyun.FileUpload;
import com.lan.interest.aliyun.GetObjectUrl;
import com.lan.interest.aliyun.TaggingImage;
import com.lan.interest.utils.MultipartFile_to_File;
import com.lan.interest.utils.ReduceImg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "TaggingImageController", description = "图像识别")
@Controller
@RequestMapping("/")
public class TaggingImageController {
    @Autowired
    FileUpload fileUpload;
    @Autowired
    GetObjectUrl getObjectUrl;
    @Autowired
    TaggingImage taggingImage;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    @ApiOperation("上传并识别")
    @RequestMapping(value = "/TaggingImage",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject backTu(@RequestParam("multipartFile")MultipartFile multipartFile) throws IOException {
        File file = new MultipartFile_to_File().transfor(multipartFile);
        file = new ReduceImg().reduce(file);
        int RandomNum =(int)(Math.random()*100);
        String saveOssPath = "img/["+formatter.format(new Date().getTime())+"]-["+RandomNum+"].jpg";
        String fileTempPath = file.getAbsolutePath();
        String[] args = new String[]{saveOssPath,fileTempPath};
        try {
            fileUpload.main(args);
            URL url = getObjectUrl.main(args); //只用到第一个参数
            JSONObject jsonObject = taggingImage.main(new String[]{url.toString()});
            file.delete();
            return jsonObject;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}