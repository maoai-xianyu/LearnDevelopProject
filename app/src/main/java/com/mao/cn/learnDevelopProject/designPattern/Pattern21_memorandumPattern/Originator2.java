package com.mao.cn.learnDevelopProject.designPattern.Pattern21_memorandumPattern;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-03-17 18:35
 */
public class Originator2 {

    private ArrayList<String> state;

    public Originator2() {
        state = new ArrayList<>();
    }

    public MementoIF createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(MementoIF mementoIF) {
        state = ((Memento) mementoIF).getState();
    }

    public void showState() {
        System.out.println("now state : " + state.toString());
    }

    public void testState1() {
        state.add("Originator2 blood - 50");
        state.add("Originator2 progress - gate1 end");
        state.add("Originator2 enemy - add");
    }

    public void testState2() {
        state.add("Originator2 blood - 450");
        state.add("Originator2 progress - gate2 end");
        state.add("Originator2 enemy - 50");
    }


    private class Memento implements MementoIF {

        private ArrayList<String> state;

        public Memento(ArrayList<String> state) {
            this.state = new ArrayList<>(state);
        }

        public ArrayList<String> getState() {
            return state;
        }

        public void setState(ArrayList<String> state) {
            this.state = state;
        }
    }
}
