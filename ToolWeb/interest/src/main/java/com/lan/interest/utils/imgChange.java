package com.lan.interest.utils;

import java.io.*;

/**
 * flag = -1,   8位深度，原通道
 * flag = 0，   8位深度，1通道
 * flag = 1，   8位深度，3通道
 * flag = 2，   原深度， 1通道
 * flag = 3，   原深度， 3通道
 * flag = 4，   8位深度，3通道
 */
public class imgChange {
    public File transfor(String imgPath,String savePath,int flag) {
        String pyFile = "D:\\java\\SpringBoot-Program\\ACG_WEB\\src\\main\\java\\com\\lan\\acg_web\\python\\imgChange.py";
        Process proc;
        try {
            String[] arg = new String[]{"python", pyFile, imgPath,savePath,String.valueOf(flag)};
            proc = Runtime.getRuntime().exec(arg);// 执行py文件
            //打印 //在测试里放同文件成功了，但现在不知道为什么不行
            new printPyMsg().print(proc);
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new File(savePath);
    }
  }

