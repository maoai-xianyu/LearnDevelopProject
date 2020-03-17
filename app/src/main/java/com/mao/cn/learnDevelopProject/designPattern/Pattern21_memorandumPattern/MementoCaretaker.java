package com.mao.cn.learnDevelopProject.designPattern.Pattern21_memorandumPattern;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2020-03-17 18:35
 */
public class MementoCaretaker {
    private HashMap<String, MementoIF> mIFHashMap;

    public MementoCaretaker() {
        mIFHashMap = new HashMap<>();
    }

    public MementoIF retrieveMemento(String name) {
        return mIFHashMap.get(name);
    }

    public void saveMemnto(String name, MementoIF mementoIF) {
        this.mIFHashMap.put(name, mementoIF);
    }
}
