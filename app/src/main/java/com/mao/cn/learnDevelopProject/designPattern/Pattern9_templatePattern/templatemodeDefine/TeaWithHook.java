package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templatemodeDefine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangkun
 * @time 2020-02-20 16:16
 */
public class TeaWithHook extends HotDrinkTemplate {

    @Override
    public void brew() {
        System.out.println("Brewing tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding lemon");
    }

    @Override
    public boolean wangCondimentsHook() {
        System.out.println("Condiments, yes or no? please input(y/n):");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String result = "";
        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !result.equals("n");
    }
}
