package com.mao.cn.learnDevelopProject.designPattern;

/**
 * @author zhangkun
 * @time 2020-03-18 14:59
 */

//数组中两个字符串的最小距离
public class MinDistance {

    int maxSubSum(int[] a) {
        int len = a.length;
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < len; i++) {
            sum += a[i];
            if (sum > 0) {
                if (sum > maxSum) {
                    maxSum = sum;
                }
            } else {
                sum = 0;
            }
        }
        return maxSum;
    }

    //获得两个字符串的最短距离
    public static int GetMinDistance(String[] strs, String str1, String str2) {
        if (str1 == null || str2 == null || strs == null) {
            return -1;
        }
        //模式串的值相同
        if (str1.equals(str2)) {
            return 0;
        }
        int last1 = -1; //模式串1初始化
        int last2 = -1; //模式串2初始化

        int min = Integer.MAX_VALUE;
        for (int i = 0; i != strs.length; i++) {
            if (strs[i].equals(str1)) {
                min = Math.min(min, last2 == -1 ? min : i - last2);
                last1 = i;
            }
            if (strs[i].equals(str2)) {
                min = Math.min(min, last1 == -1 ? min : i - last1);
                last2 = i;
            }


        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        String[] strs = {"1", "3", "3", "3", "2", "3", "1"};
        String str1 = "1";
        String str2 = "2";
        System.out.println(GetMinDistance(strs, str1, str2));

    }
}