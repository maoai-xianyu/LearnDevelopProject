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
import com.mao.cn.learnDevelopProject.wedget.spannerString.AnnotationTextView;
import com.mao.cn.learnDevelopProject.wedget.spannerString.ClickableColorSpan;
import com.mao.cn.learnDevelopProject.wedget.spannerString.KeyWordClickable;
import com.mao.cn.learnDevelopProject.wedget.spannerString.SPAnnotationTextView;
import com.mao.cn.learnDevelopProject.wedget.spannerString.WordResuorceU;

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
    @BindView(R.id.atv_content)
    AnnotationTextView atvContent;
    @BindView(R.id.sp_atv_content)
    SPAnnotationTextView spAtvContent;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_stringspanner_click;
    }

    @Override
    public void initView() {

        String str = "Hey, look! Shooting stars[^1].";
        String strNoAnn = "Hey, look! Shooting stars.";

        String strAll = "Hey, look! Shooting stars[^1].\nOh, oh. Quick, quick. Make a wish. Make a wish. You gotta[^2] make a wish. \nWow, my wish came true. \nI'm okay.\nMine too.\n[^1]流星雨\n[^2]必须（gotta等于got to, 是口语化表达）";

        atvContent.setAnnotationText(str, WordResuorceU.getAnnotation(strAll));


        spAtvContent.setAnnotationText(strNoAnn, WordResuorceU.getAnnotation(strAll));

        //getData();
        //tvShowContent.setText(getClickableSpan());
        //tvShowContent.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
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
