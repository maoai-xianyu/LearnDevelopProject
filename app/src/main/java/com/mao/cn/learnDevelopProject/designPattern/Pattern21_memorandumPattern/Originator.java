package com.mao.cn.learnDevelopProject.designPattern.Pattern21_memorandumPattern;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2020-03-17 18:35
 */
public class Originator {

    private HashMap<String, String> state;

    public Originator() {
        state = new HashMap<>();
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

    public void testState1(){
        state.put("blood","50");
        state.put("progress","gate1 end");
        state.put("enemy","5");
    }

    public void testState2(){
        state.put("blood","450");
        state.put("progress","gate2 end");
        state.put("enemy","50");
    }


    private class Memento implements MementoIF {

        private HashMap<String, String> state;

        public Memento(HashMap<String, String> state) {
            this.state = new HashMap<>(state);
        }

        public HashMap<String, String> getState() {
            return state;
        }

        public void setState(HashMap<String, String> state) {
            this.state = state;
        }
    }
}
