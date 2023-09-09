package com.lan.interest.Example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pythonExample {
    public static void main(String[] args) {
        //new grey_to_colour().transfor(new File("D:\\java\\SpringBoot-Program\\ACG_WEB\\src\\main\\resources\\static\\img\\jj.png"));

        // TODO Auto-generated method stub
        Process proc;
        try {
            /* 无参调用python
            //这里传的是命令 不是py文件的地址 参数应该是"python (py文件地址)"
            proc = Runtime.getRuntime().exec("python D:\\java\\SpringBoot-Program\\ACG_WEB\\src\\main\\java\\com\\lan\\acg_web\\python\\pythonExample.py");// 执行py文件
           */

            //有参调用python
            int a = 18;
            int b = 23;
            String[] args1 = new String[] { "python","D:\\java\\SpringBoot-Program\\ACG_WEB\\src\\main\\java\\com\\lan\\acg_web\\python\\example\\pythonExample.py", String.valueOf(a), String.valueOf(b) };
            proc = Runtime.getRuntime().exec(args1);// 执行py文件


            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
