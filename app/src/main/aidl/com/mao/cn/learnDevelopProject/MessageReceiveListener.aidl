// MessageReceiveListener.aidl
package com.mao.cn.learnDevelopProject;

// 一个AIDL文件要使用另一个AIDL文件，需要import进这个文件
import com.mao.cn.learnDevelopProject.model.Message;

// 接收服务端 收到消息的回调
interface MessageReceiveListener {

    void onReceiveMessage(in Message message);

}
