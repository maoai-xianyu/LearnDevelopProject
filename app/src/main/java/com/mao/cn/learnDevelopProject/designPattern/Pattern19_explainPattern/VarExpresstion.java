package com.mao.cn.learnDevelopProject.designPattern.Pattern19_explainPattern;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2020-03-03 11:52
 */
public class VarExpresstion extends AbstractExpresstion {

    private String key;

    public VarExpresstion(String _key) {
        this.key = _key;
    }

    @Override
    public Float interpreter(HashMap<String, Float> var) {
        return var.get(this.key);
    }
}
