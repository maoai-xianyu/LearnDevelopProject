package com.mao.cn.learnDevelopProject.designPattern.Pattern18_flyquantityPattern.oo;

/**
 * @author zhangkun
 * @time 2020-03-02 14:00
 */
public class OoTrees {

    private int length = 10000000;
    private OoTree[] mOoTrees = new OoTree[length];

    public OoTrees() {

        for (int i = 0; i < length; i++) {
            mOoTrees[i] = new OoTree((int) (Math.random() * length),
                    (int) (Math.random() * length),
                    (int) (Math.random() * length) % 5);
        }
    }

    public void display() {
        for (int i = 0; i < mOoTrees.length; i++) {
            mOoTrees[i].display();
        }
    }

}
