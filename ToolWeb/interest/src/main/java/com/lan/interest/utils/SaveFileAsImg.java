package com.lan.interest.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class SaveFileAsImg {
    public void save(File file,String savePath){
        try {
            InputStream inputStream = FileUtils.openInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            byte[] buffer = new byte[1000];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("保存文件成功");
        }catch (Exception e){
            System.out.println("保存文件出错");
        }
    }
}

