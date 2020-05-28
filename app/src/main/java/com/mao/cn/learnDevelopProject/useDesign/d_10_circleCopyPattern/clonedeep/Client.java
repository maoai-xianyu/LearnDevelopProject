package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.clonedeep;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

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
        user.mAddress = new Address("山西大同", "大同");

        // 深拷贝
        try {
            // 拷贝对象
            User copyUser = user.clone();
            System.out.println("user " + user.userName + "  地址 address " + user.mAddress.addressName);
            System.out.println("copyUser " + copyUser.userName + "  地址 address " + copyUser.mAddress.addressName);

            System.out.println("--------");
            copyUser.mAddress.addressName = "北京北京";
            copyUser.userName = "大丹";

            System.out.println("user " + user.userName + "  地址 address " + user.mAddress.addressName);
            System.out.println("copyUser " + copyUser.userName + "  地址 address " + copyUser.mAddress.addressName);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item" + i);
        }

        ArrayList<String> listClone = (ArrayList<String>) list.clone();

        for (String item : listClone) {
            System.out.println("item "+item);

        }

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.newBuilder();


    }
}
