package com.lan.interest.utils;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


public class ReduceImg {
    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     */
    public File reduce(File srcfile) {
        try {
            System.out.println("压缩图像");
            System.out.println("   压缩前: "+srcfile.length()/1000+" KB");
            int[] results = getImgWidthHeight(srcfile);
            int widthDist = results[0];
            int heightDist = results[1];
            Image src = ImageIO.read(srcfile);
            String imgDist = srcfile.getAbsolutePath();
            BufferedImage tag = new BufferedImage(widthDist, heightDist, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src.getScaledInstance(widthDist, heightDist, Image.SCALE_SMOOTH), 0, 0, null); //缩放平滑
            FileOutputStream out = new FileOutputStream(imgDist);
            //将图片按JPEG压缩，保存到out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            //关闭文件输出流
            out.close();
            File img = new File(imgDist);
            System.out.println("   压缩后: "+img.length()/1000+" KB");
            return img;
        } catch (Exception ef) {
            ef.printStackTrace();
        }
        return null;
    }

    /**
     * 获取图片宽度和高度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is ;
        BufferedImage src ;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);

            src = ImageIO.read(is);

            result[0] =src.getWidth(null);

            result[1] =src.getHeight(null);
            is.close();
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

}