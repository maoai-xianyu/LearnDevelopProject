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
import com.mao.cn.learnDevelopProject.component.DaggerMainGuideComponent;
import com.mao.cn.learnDevelopProject.modules.MainGuideModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMainGuide;
import com.mao.cn.learnDevelopProject.ui.presenter.MainGuidePresenter;

import javax.inject.Inject;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class MainGuideFragment extends BaseFragment implements IMainGuide {

    @Inject
    MainGuidePresenter presenter;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_main_guide;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainGuideComponent.builder()
                .appComponent(appComponent)
                .mainGuideModule(new MainGuideModule(this))
                .build().inject(this);

    }

    @Override
    public void setListener() {

    }
}
