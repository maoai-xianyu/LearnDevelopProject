package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.enumerationAdapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author zhangkun
 * @time 2020-02-19 11:46
 */
public class EnumerationIterator implements Iterator<Object> {

    private Enumeration enumeration;

    public EnumerationIterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }

}
