package com.mao.cn.learnDevelopProject.designPattern.Pattern19_explainPattern;

/**
 * @author zhangkun
 * @time 2020-03-03 11:55
 */
public abstract class SymbolExpression extends AbstractExpresstion {

    protected AbstractExpresstion left;
    protected AbstractExpresstion right;

    public SymbolExpression(AbstractExpresstion _left, AbstractExpresstion _right) {
        this.left = _left;
        this.right = _right;
    }
}
