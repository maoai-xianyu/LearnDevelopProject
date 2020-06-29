package com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhangkun
 * @time 2020-05-16 15:30
 * @Description 导航栏的规范
 */
public interface INavigation {


    public void createNavigationBar();


    /**
     * 将 NavigationBar 添加到父布局
     * @param navigationBar
     * @param parent
     */
    public void attachParent(View navigationBar, ViewGroup parent);

    /**
     * 绑定参数
     */
    public void attachNavigationParams();

}
