package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.flyPatternExtend;

/**
 * @author zhangkun
 * @time 2020-03-02 15:29
 */
public class MainTest {
    public static void main(String[] args) {
        showMemInfo();
        PlantManager plantManager = new PlantManager();
        showMemInfo();
        plantManager.display();
        showMemInfo();
    }

    private static void showMemInfo() {
        // 最大内存
        long max = Runtime.getRuntime().maxMemory();
        // 分配内存
        long total = Runtime.getRuntime().totalMemory();
        // 已分配内存中的剩余空间
        long free = Runtime.getRuntime().freeMemory();
        // 已占用的内存
        long used = total - free;
        System.out.println("最大内存 = " + max);
        System.out.println("已分配内存 = " + total);
        System.out.println("已分配内存中的剩余空间 = " + free);
        System.out.println("已占用的内存 = " + used);
        System.out.println("时间 = " + System.currentTimeMillis());
        System.out.println("");

    }
}
