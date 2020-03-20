package com.mao.cn.learnDevelopProject.ui.activity.aspectJ;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author zhangkun
 * @time 2020-03-19 16:58
 * 处理切点
 */
@Aspect
public class SectionAspect {

    /**
     * 找到处理的切点
     * * *(..) 可以处理的所有方法
     */
    @Pointcut("execution(@com.mao.cn.learnDevelopProject.ui.activity.aspectJ.CheckNet * *(..))")
    public void checkNetBehavior() {

    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        LogU.e("check net");
        // 做埋点  日志上传  权限检测 RxPermission 网络检测
        // 1. 获取 CheckNet 注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);

        if (checkNet != null) {
            // 2. 判断有木有网络  获取context
            Object object = joinPoint.getThis(); // view  activity fragment  当前切点方法所在的类
            Context context = getContent(object);
            if (context != null){
                if (!isNetworkAvailable(context)){
                    // 3. 没有网络不要往下进行
                    ToastUtils.show("请检测您的网络！");
                    return null;
                }
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContent(Object object) {
        if (object instanceof View) {
            return ((View) object).getContext();
        } else if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }


    public static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
