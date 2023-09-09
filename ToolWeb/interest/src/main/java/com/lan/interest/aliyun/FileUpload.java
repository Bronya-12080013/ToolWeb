package com.lan.interest.aliyun;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/** 简单上传
 使用文件上传，您可以将本地文件上传到OSS文件。
 https://help.aliyun.com/document_detail/84781.htm?spm=a2c4g.11186623.0.0.799d24cbaSdi5j#concept-84781-zh
 */

  /**
            注意，文件上传方法 FileUpload涉及到oss的账号密码
            （无论是新new还是@Autowired，FileUpload类里面都用到了@Value）
            只有启动springboot项目(项目主类)
            按照它的方法调用(有@Controller的Controller类)
            才能自动从yml文件里取出值来
            直接使用别的类方法调用而不启动springboot项目，会报错说找不到密码的
             */

@Component
public class FileUpload {
    @Value("${aliyun.oss.endpoint}")
    private String oss_endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String oss_accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private  String oss_accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String oss_bucketName;


    public void main(String[] args) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。  //Endpoint:端点
        //我用华东2（上海）
        String endpoint = oss_endpoint;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = oss_accessKeyId;
        String accessKeySecret = oss_accessKeySecret;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = oss_bucketName;
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        //上传的文件的文件名。可以包含多级目录。例如a/b/c/xxx.jpg
        String objectName = args[0];
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath= args[1];

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);
            // 上传文件。
            ossClient.putObject(putObjectRequest);
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
    }
}