package com.lan.interest.controller;

import com.lan.interest.service.EdgeDetectionService;
import com.lan.interest.utils.MultipartFile_to_File;
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

/**
 * 传入mp4地址
 */
@Api(tags = "edge_detection_controller",description = "边缘检测")
@Controller
@RequestMapping(value = "/")
public class edge_detection_controller {

    @Autowired
    private EdgeDetectionService edgeDetectionService;

    @ApiOperation("mp4边缘检测并生成视频")
    @RequestMapping(value = "/edgeDetection",method = RequestMethod.POST)
    @ResponseBody
    public String edge_detection(@RequestParam("mp4_File") MultipartFile mp4_file,
                                 @RequestParam("fbs") int fbs){
        File mp4file = new MultipartFile_to_File().transfor(mp4_file);
        String mp4Path = mp4file.getAbsolutePath();
        return edgeDetectionService.edgeDetection(mp4Path,fbs);
    }
}
