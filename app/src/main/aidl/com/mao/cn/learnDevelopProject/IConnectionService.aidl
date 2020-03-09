// IConnectionService.aidl
package com.mao.cn.learnDevelopProject;


// 链接服务
interface IConnectionService {

   oneway void connect();

   void disconnect();

   boolean isConnected();

}
