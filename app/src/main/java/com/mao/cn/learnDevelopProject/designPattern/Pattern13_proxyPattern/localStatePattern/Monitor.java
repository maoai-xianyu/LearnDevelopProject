package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.localStatePattern;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-02-24 10:02
 */
public class Monitor {
    private ArrayList<CandyMachine> mCandyMachines;

    public Monitor() {
        mCandyMachines = new ArrayList<>();
    }

    public void addMachine(CandyMachine candyMachine) {
        mCandyMachines.add(candyMachine);
    }

    public void report() {
        CandyMachine candyMachine;
        for (int i = 0; i < mCandyMachines.size(); i++) {
            candyMachine = mCandyMachines.get(i);
            System.out.println("Machine loc:" + candyMachine.getLocation());
            System.out.println("Machine Candy count:" + candyMachine.getCount());
            System.out.print("Machine state:");
            candyMachine.getState().printstate();
        }
    }
}
