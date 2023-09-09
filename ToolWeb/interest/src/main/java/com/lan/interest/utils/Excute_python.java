package com.lan.interest.utils;

import java.io.IOException;

public class Excute_python {
    public static void excute(String[] args){
        String [] argAll = new String[1+args.length];
        argAll[0]="python";
        for(int i=1;i<argAll.length;i++)
        {
            argAll[i]=args[i-1];
            // System.out.println(argAll[i]);
        }
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(argAll);
            new printPyMsg().print(proc);
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
