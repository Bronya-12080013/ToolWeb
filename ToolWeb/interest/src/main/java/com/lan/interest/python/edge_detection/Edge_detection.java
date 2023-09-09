package com.lan.interest.python.edge_detection;

import com.lan.interest.utils.Excute_python;

public class Edge_detection {
    public static void excute(String mp4Path,String imgPath){
        System.out.println("请等待视频播放结束，摁q可提前结束...");
        new Excute_python().excute(new String[]{"interest/src/main/java/com/lan/interest/python/edge_detection/edge_detection.py", mp4Path,imgPath});
    }
}
