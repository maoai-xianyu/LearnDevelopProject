// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/11/2017 11:53 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;

/**
 * DecorView将内容显示在PhoneWindow上，
 * 并通过WindowManagerService来进行接收，并通过Activity对象来回调对应的onClickListener。
 * 显示时，将屏幕分成两个部分，TitleView和ContentView。
 */
public class DefineView3Fragment extends BaseFragment implements IMainGuide {


    public static DefineView3Fragment getInstance() {
        return new DefineView3Fragment();
    }

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_view3;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {


    }

    @Override
    public void setListener() {
        /*RxView.clicks(btnPlay).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            play();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
*/
    }

    /**
     * 一个View显示在屏幕上需要经历三个流程：测量，布局，绘制
     * 1、测量的目的在于告诉系统绘制一个多大的，位置在哪里。这个过程在onMeasure()方法中进行。
     * 2、测量主要依赖MeasureSppec类。MeasureSpec是一个32位的int值，高2位为测量的模式，低30位为测量的大小。
     * 3、测量的模式共有三种：
     *    a、EXACTLY 精确模式，两种情况：控件的layout_width，layout_height 指定数值时，例如layout_width="100dp" 指定为match_parent
     *    b、AT_MOST 最大值模式：控件的layout_width，layout_height指定为wrap_content
     *    c、UNSPECIFIED 未指明模式 不指定控件大小，View想多大就多大。
     */

}
