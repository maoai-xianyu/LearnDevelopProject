package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templateAround;

/**
 * @author zhangkun
 * @time 2020-02-20 16:47
 */
public class ArrayDuck implements Comparable {

    private String name = "";
    private int weight = 1;

    public ArrayDuck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        return name + " weight:" + weight;
    }

    @Override
    public int compareTo(Object o) {
        ArrayDuck duck = (ArrayDuck) o;
        if (this.weight > duck.weight) {
            return 1;
        } else if (this.weight < duck.weight) {
            return -1;
        }
        return 0;
    }
}
