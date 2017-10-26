package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;
import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2017/10/26.
 */

public class TransLateBaiDu implements Serializable{

    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"apple","dst":"苹果"}]
     */
    private String from;
    private String to;
    private List<TransLateBaiDuDetail> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransLateBaiDuDetail> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransLateBaiDuDetail> trans_result) {
        this.trans_result = trans_result;
    }

    @Override
    public String toString() {
        return "TransLateBaiDu{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                '}';
    }
}
