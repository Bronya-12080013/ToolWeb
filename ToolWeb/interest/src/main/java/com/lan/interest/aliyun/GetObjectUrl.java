package com.lan.interest.aliyun;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;

@Component
public class GetObjectUrl {

    @Value("${aliyun.oss.endpoint}")
    private String oss_endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String oss_accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private  String oss_accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String oss_bucketName;

    public URL main(String[] args) throws Throwable {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = oss_endpoint;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = oss_accessKeyId;
        String accessKeySecret = oss_accessKeySecret;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = oss_bucketName;
        // 填写Object完整路径，例如exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = args[0];

        // 从STS服务获取临时访问凭证后，您可以通过临时访问密钥和安全令牌生成OSSClient。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 设置签名URL过期时间，单位为毫秒。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            System.out.println("OSS地址"+"\n   "+url);
            return url;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}