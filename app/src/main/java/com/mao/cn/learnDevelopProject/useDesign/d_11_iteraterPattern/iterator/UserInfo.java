package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.iterator;

/**
 * @author zhangkun
 * @time 2020-05-28 15:52
 * @Description
 */
public class UserInfo {


    public String userName;
    public String userPsd;
    public String userId;
    public String userSex;

    public UserInfo(String userName, String userPsd, String userId, String userSex) {
        this.userName = userName;
        this.userPsd = userPsd;
        this.userId = userId;
        this.userSex = userSex;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userPsd='" + userPsd + '\'' +
                ", userId='" + userId + '\'' +
                ", userSex='" + userSex + '\'' +
                '}';
    }
}
