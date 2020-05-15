package com.mao.cn.learnDevelopProject.java;

/**
 * @author zhangkun
 * @time 2020-03-27 12:01
 */
public class TestPrint {

    public static void main(String[] args) {
        print(3);
    }


    public static int getNum(int n, int i, int j) {
        if (i == 0) {
            return j + 1;
        } else if (i == n - 1) {
            return 3 * n - j - 2;
        } else if (j == 0) {
            return 4 * n - i - 3;
        } else if (j == n - 1) {
            return n + 1;
        } else {
            return 4 * (n - 1) + getNum(n - 2, i - 1, j - 1);
        }
    }

    public static void print(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d", getNum(n, i, j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
