package com.mao.cn.learnDevelopProject.designPattern.Pattern21_memorandumPattern;

/**
 * @author zhangkun
 * @time 2020-03-17 18:34
 */
public class MainTest {

    public static void main(String[] args) {

        MementoCaretaker mementoCaretaker = new MementoCaretaker();

        Originator originator = new Originator();
        Originator2 originator2 = new Originator2();

        System.out.println("****originator*****");
        originator.testState1();
        mementoCaretaker.saveMemnto("originator",originator.createMemento());
        originator.showState();
        originator.testState2();
        originator.showState();
        originator.restoreMemento(mementoCaretaker.retrieveMemento("originator"));
        originator.showState();

        System.out.println("****originator2*****");

        originator2.testState1();
        mementoCaretaker.saveMemnto("originator2",originator2.createMemento());
        originator2.showState();
        originator2.testState2();
        originator2.showState();
        originator2.restoreMemento(mementoCaretaker.retrieveMemento("originator2"));
        originator2.showState();

    }
}
