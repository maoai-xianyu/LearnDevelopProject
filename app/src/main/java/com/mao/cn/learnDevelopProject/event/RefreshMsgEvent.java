package com.mao.cn.learnDevelopProject.event;

/**
 * author:  zhangkun .
 * date:    on 2018/6/12.
 */
public class RefreshMsgEvent {
    private String numMsg;
    private boolean show;

    public String getNumMsg() {
        return numMsg;
    }

    public void setNumMsg(String numMsg) {
        this.numMsg = numMsg;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
