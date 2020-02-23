package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author zhangkun
 * @time 2020-02-23 17:30
 */
public class ComposeIterator implements Iterator {

    private Stack<Iterator> mStack = new Stack<>();

    public ComposeIterator(Iterator iterator) {
        mStack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (mStack.isEmpty()) {
            return false;
        }
        Iterator iterator = mStack.peek();
        if (!iterator.hasNext()) {
            mStack.pop();
            return hasNext();
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = mStack.peek();
            MenuComponent menuComponent = (MenuComponent) iterator.next();
            mStack.push(menuComponent.getIterator());
            return menuComponent;
        }
        return null;
    }
}
