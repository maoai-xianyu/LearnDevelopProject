package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;

/**
 * author:  zhangkun .
 * date:    on 2017/10/26.
 */

public class TransLateBaiDuDetail implements Serializable {


    /**
     * src : apple
     * dst : 苹果
     */
    private String src;
    private String dst;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "TransLateBaiDuDetail{" +
                "src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }
}
