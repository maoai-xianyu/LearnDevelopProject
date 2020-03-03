package com.mao.cn.learnDevelopProject.designPattern.Pattern19_explainPattern;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2020-03-03 11:56
 */
public class MultiExpression extends SymbolExpression {

    public MultiExpression(AbstractExpresstion _left, AbstractExpresstion _right) {
        super(_left, _right);
    }

    @Override
    public Float interpreter(HashMap<String, Float> var) {
        return super.left.interpreter(var) * super.right.interpreter(var);
    }
}
