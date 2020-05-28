package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.clone;

/**
 * @author zhangkun
 * @time 2020-05-28 11:23
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        User user = new User();

        user.age = 18;
        user.userName = "大南";
        user.mAddress = new User.Address("山西大同","大同");

        // 浅拷贝
        try {
            // 拷贝对象
            User copyUser = user.clone();
            System.out.println("user "+user.userName+"  地址 address "+user.mAddress.addressName);
            System.out.println("copyUser "+copyUser.userName+"  地址 address "+copyUser.mAddress.addressName);

            System.out.println("--------");
            copyUser.mAddress.addressName = "北京北京";
            copyUser.userName="大丹";

            System.out.println("user "+user.userName+"  地址 address "+user.mAddress.addressName);
            System.out.println("copyUser "+copyUser.userName+"  地址 address "+copyUser.mAddress.addressName);

            // 现象： user 的名字没有变 大南  copyUser 的名字为 大丹
            // 但是： user.mAddress.addressName 变成了 北京北京 copyUser.mAddress.addressName 也是 北京北京
            // 浅拷贝，就是类的类对象实例(User中的Address对象)，是没有被拷贝的，还是共用一份
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }
}
