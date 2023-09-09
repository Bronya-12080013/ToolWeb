package com.lan.interest.utils;

import java.io.File;

public class DeleteFile {
    /**
     * 注意！！！！ 参数file为文件夹 new的时候参数为路径，以'/'结尾
     * @param file
     * @return
     */
    public static boolean delete(File file) {
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            System.out.println("文件删除失败,请检查文件是否存在以及文件路径是否正确");
            return false;
        }
        //获取目录下子文件
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                //递归删除目录下的文件
                delete(f);
            } else {
                //文件删除
                f.delete();
                //打印文件名
                //System.out.println("已删除的文件：" + f.getName());
            }
        }
        //文件夹删除
        file.delete();
        // System.out.println("已删除的目录：" + file.getName());
        return true;
    }
}
