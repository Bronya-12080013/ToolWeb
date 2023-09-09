package com.lan.interest.aliyun;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.imagerecog20190930.models.TaggingImageRequest;
import com.aliyun.imagerecog20190930.models.TaggingImageResponse;
import com.aliyun.imagerecog20190930.models.TaggingImageResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 阿里云 图像识别/图像打标TaggingImage
 *
 */

@Component
public class TaggingImage {
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */

    @Value("${aliyun.oss.endpoint}")
    private String oss_endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String oss_accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private  String oss_accessKeySecret;


    public static com.aliyun.imagerecog20190930.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint ="imagerecog.cn-shanghai.aliyuncs.com";
        return new com.aliyun.imagerecog20190930.Client(config);
    }

    public  JSONObject main(String[] args_) throws Exception {
        List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.imagerecog20190930.Client client = TaggingImage.createClient(oss_accessKeyId, oss_accessKeySecret);
        TaggingImageRequest taggingImageRequest = new TaggingImageRequest()
                .setImageURL(args.get(0));
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            TaggingImageResponse back = client.taggingImageWithOptions(taggingImageRequest, runtime);
            JSONObject jsonData = print(back);
            return jsonData;
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            System.out.println("出错了");
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            System.out.println("出错了");
        }
        return null;
    }

    static  JSONObject print(TaggingImageResponse back){
        JSONObject jsonData = new JSONObject();
        System.out.println("图像识别结果");
        TaggingImageResponseBody body = back.getBody();
        TaggingImageResponseBody.TaggingImageResponseBodyData data = body.getData();
        List<TaggingImageResponseBody.TaggingImageResponseBodyDataTags> tags = data.getTags();
        for(int i=0;i<tags.size();i++){
            TaggingImageResponseBody.TaggingImageResponseBodyDataTags tag = tags.get(i);
            String value = tag.getValue();
            Float confidence=tag.getConfidence();
            System.out.println(new StringBuffer().append("   ").append(value).append(" +").append(confidence));
            jsonData.put(value,confidence);
        }
        return jsonData;
    }
}