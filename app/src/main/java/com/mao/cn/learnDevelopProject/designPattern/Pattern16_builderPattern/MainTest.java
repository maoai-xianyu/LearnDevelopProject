package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder.Builder3d;
import com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder.Builder4d;
import com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder.BuilderSelf;

/**
 * @author zhangkun
 * @time 2020-02-27 13:47
 */
public class MainTest {

    public static void main(String[] args) {
        Director director = new Director(new Builder4d("2019-12-12"));
        director.construct();
        System.out.println("************************");
        director.setAbsBuilder(new Builder3d("2019-12-20"));
        director.construct();

        System.out.println("************************");
        testSelf();

        String a = "你好";
        a = "hello";
        System.out.println(
                a
        );

    }

    public static void testSelf() {
        BuilderSelf builder = new BuilderSelf("2015-9-29");


        builder.addTicket("Plane Ticket")
                .addEvent("ly to destination")
                .addEvent("supper")
                .addEvent("dancing")
                .addHotel("Four Sessions");


        builder.addDay()
                .addTicket("Theme park")
                .addEvent("Bus to park")
                .addEvent("lunch")
                .addHotel("Four Sessions");

        builder.addDay();

        builder.addTicket("Plane Ticket")
                .addEvent("City Tour")
                .addEvent("Fly to Home").addHotel("Home in");

        builder.getVacation().showInfo();
    }
}
