package com.lan.interest.python.edge_detection;

import com.lan.interest.utils.Excute_python;

/**
 * 失败了
 * 只能从终端执行py文件
 * (执行python命令：python+py文件路径+参数)
 * 如python src/main/java/com/lan/acg_web/python/edge_detection/video_compression.py src/main/java/com/lan/acg_web/test video.mp4
 * 用这个java方法不能调用 原因不明
 *
 */

public class Video_compression {
    /** 魔改别人的代码 参数用着有点不习惯 不过看样子filePath还涉及操作系统的不同，就不乱改它了
     *压缩视频文件 新生产的文件以 "new_" + inputName命名 放在原视频文件同级目录下
     * @param filePath 视频文件所在文件夹路径 是否以'/'结尾都可以
     * @param inputName 视频文件名称 如"video.mp4"
     */
    public static void comp(String filePath,String inputName) {
        Excute_python.excute(new String[]{"interest/src/main/java/com/lan/interest/python/edge_detection/video_compression.py",filePath,inputName});
    }
}