package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templateAround;

/**
 * @author zhangkun
 * @time 2020-02-20 16:43
 */
public abstract class SortTemplate {

    public final void sort(Object[] objects) {
        for (int i = 0, len = objects.length - 1; i < len; i++) {
            if (compare(objects[i + 1]) > 0) {
                Object temp = objects[i];
                objects[i] = objects[i + 1];
                objects[i + 1] = temp;
            }
        }
    }

    public abstract int compare(Object object);
}
