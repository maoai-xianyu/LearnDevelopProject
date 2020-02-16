package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.javaDefine;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author zhangkun
 * @time 2020-02-16 18:11
 */
public class InputStreamTest {
    public static void main(String[] args) {

        int c;

        try {
            InputStream inputStream = new UpperCaseInputStream(
                    new BufferedInputStream(new FileInputStream("/Users/zhangkun/workspace/LearnMyDevelopProject/app/src/main/java/com/mao/cn/learnDevelopProject/designPattern/Pattern3_decoratePattern/javaDefine/test.txt")));
            while ((c = inputStream.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
