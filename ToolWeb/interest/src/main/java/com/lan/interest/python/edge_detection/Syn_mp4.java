package com.lan.interest.python.edge_detection;

import com.lan.interest.utils.Excute_python;

import java.io.File;

public class Syn_mp4 {
    public static void syn(  String imgPath,String saveMp4,int fbs) {
        System.out.println("合成mp4中...");
        Excute_python.excute(new String[] {"interest/src/main/java/com/lan/interest/python/edge_detection/syn_mp4.py", imgPath,saveMp4,String.valueOf(fbs)});
        if (new File(saveMp4).exists()) System.out.println("合成mp4成功");
        else System.out.println("合成mp4失败");
    }
}
