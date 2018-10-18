package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;

/**
 * author:  zhangkun .
 * date:    on 2018/10/17.
 */
public class CastsAndAvators implements Serializable {

    private  Casts casts;

    private CastsAvators castsAvators;

    public Casts getCasts() {
        return casts;
    }

    public void setCasts(Casts casts) {
        this.casts = casts;
    }

    public CastsAvators getCastsAvators() {
        return castsAvators;
    }

    public void setCastsAvators(CastsAvators castsAvators) {
        this.castsAvators = castsAvators;
    }
}
