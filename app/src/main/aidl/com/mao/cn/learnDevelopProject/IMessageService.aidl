// IMessageService.aidl
package com.mao.cn.learnDevelopProject;

// 一个AIDL文件要使用另一个AIDL文件，需要import进这个文件
import com.mao.cn.learnDevelopProject.model.Message;
import com.mao.cn.learnDevelopProject.MessageReceiveListener;

// 消息服务
interface IMessageService {

    // 自己定义的实体类需要用 in 修饰，基本数据类型不需要  子不影响主
    // 自己定义的实体类需要用 inout 修饰，基本数据类型不需要  主和子相互影响
    void sendMessage(inout Message message);

    // MessageReceiveListener 是 AIDL的接口，不是实体类，不需要用关键字修饰
    void registerMessageReceiveListener(MessageReceiveListener messageReceiveListener);

    void unRegisterMessageReceiveListener(MessageReceiveListener messageReceiveListener);
}
