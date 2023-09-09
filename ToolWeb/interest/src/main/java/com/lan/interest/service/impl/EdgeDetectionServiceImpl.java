package com.lan.interest.service.impl;

import com.lan.interest.aliyun.FileUpload;
import com.lan.interest.aliyun.GetObjectUrl;
import com.lan.interest.python.edge_detection.Edge_detection;
import com.lan.interest.python.edge_detection.Syn_mp4;
import com.lan.interest.service.EdgeDetectionService;
import com.lan.interest.utils.DeleteFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
地址是一大问题
肯定最后不用绝对地址
之前我用src开始的相对地址，可行
过一段时间后，变成子项目后，改过地址后就不行了。又得改回绝对地址
===========
总之，若有问题，多多考虑是不是地址错了
 */

//注意：上传的文件有各种奇怪的要求，这里不细究了。如：Prefix string too short

@Service
public class EdgeDetectionServiceImpl implements EdgeDetectionService {
    @Autowired
    FileUpload fileUpload;
    @Autowired
    GetObjectUrl getObjectUrl;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    @Override
    public String edgeDetection(String mp4Path,int fbs){
            // 方法  File.mkdir(); 新建文件夹，路径最后是要有/ 不然File会识别成一个文件
            String imgPath = "interest/src/main/java/com/lan/interest/python/edge_detection/img/";
            File imgDir = new File(imgPath);
            if (imgDir.exists()) DeleteFile.delete(imgDir);
            imgDir.mkdir();

            String saveMp4 = "interest/src/main/java/com/lan/interest/python/edge_detection/video/";
            File videoDir = new File(saveMp4);
             if (videoDir.exists()) DeleteFile.delete(videoDir);
            videoDir.mkdir();

            saveMp4 = new StringBuffer().append(saveMp4).append("video.mp4").toString();

            Edge_detection.excute(mp4Path,imgPath);
            Syn_mp4.syn(imgPath,saveMp4,fbs);
            URL url = null;

            if (new File(saveMp4).exists()) {
                try {
            /*
            注意，文件上传方法 FileUpload涉及到oss的账号密码
            （无论是新new还是@Autowired，FileUpload类里面都用到了@Value）
            只有启动springboot项目(项目主类)
            按照它的方法调用(有@Controller的Controller类)
            才能自动从yml文件里取出值来
            直接使用别的类方法调用而不启动springboot项目，会报错说找不到密码的
             */
                    int RandomNum = (int) (Math.random() * 100);
                    String oss_path = "video/[" + formatter.format(new Date().getTime()) + "]-[" + RandomNum + "].mp4";
                    fileUpload.main(new String[]{oss_path, saveMp4});
                    url = getObjectUrl.main(new String[]{oss_path});
                } catch (Exception exception) {
                    exception.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        DeleteFile.delete(imgDir);
        DeleteFile.delete(videoDir);
        return url.toString() ;
    }


}