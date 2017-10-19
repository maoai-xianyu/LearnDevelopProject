// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerStringSpannerClickComponent;
import com.mao.cn.learnDevelopProject.modules.StringSpannerClickModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IStringSpannerClick;
import com.mao.cn.learnDevelopProject.ui.presenter.StringSpannerClickPresenter;
import com.mao.cn.learnDevelopProject.wedget.spannerString.ClickableColorSpan;
import com.mao.cn.learnDevelopProject.wedget.spannerString.KeyWordClickable;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class StringSpannerClickActivity extends BaseActivity implements IStringSpannerClick {

    @Inject
    StringSpannerClickPresenter presenter;

    @BindView(R.id.tv_show_content)
    TextView tvShowContent;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_stringspanner_click;
    }

    @Override
    public void initView() {

        getData();
        /*tvShowContent.setText(getClickableSpan());
        tvShowContent.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效*/
    }

    @Override
    public void setListener() {
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerStringSpannerClickComponent.builder().appComponent(appComponent).stringSpannerClickModule(new StringSpannerClickModule(this)).build().inject(this);

    }

    private SpannableString getClickableSpan() {
        SpannableString spanableInfo = new SpannableString(
                "This is a test, Click Me");
        int start = 16;
        int end = spanableInfo.length();
        ClickableColorSpan clickableColorSpan = new ClickableColorSpan();
        clickableColorSpan.setmListener(v -> onTip("You are clicking ~"));
        spanableInfo.setSpan(clickableColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }


    private void getData() {
        String ss = "asd#asdas#中国#商店就分#测试#hello#word#asdasdasd#中asd国#商店as";
//        tvShowContent.setText("#中国#商店就分#测试#hello#word#");
        //设置关键字 点击操作 ##中的文字为关键字
        KeyWordClickable.setKeyworldClickable(tvShowContent, ss, KeyWordClickable.topicPattern, this);
    }

}
