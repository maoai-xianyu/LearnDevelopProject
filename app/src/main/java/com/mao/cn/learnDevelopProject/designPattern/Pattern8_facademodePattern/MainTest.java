package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode.HomeTheaterFacade;

/**
 * @author zhangkun
 * @time 2020-02-20 11:09
 */
public class MainTest {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
    }
}
