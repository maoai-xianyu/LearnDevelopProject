package com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBackgroundSpan;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBackgroundSpanString;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBgSpan;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import butterknife.BindView;

import static com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBgSpan.STYLE_STROCK;

/**
 * @author zhangkun
 * @time 2020/9/27 10:16 AM
 * @Description
 */
public class MovieMainFragment extends BaseFragment {

    @BindView(R.id.tvDest111)
    TextView mText;
    @BindView(R.id.tvDest111111)
    TextView mText11;

    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_movie_main;
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void initView() {


        int radiusColor = getResources().getColor(R.color.colorAccent);
        String content = getString(R.string.content1);
        SpannableString spanString = new SpannableString(content);
        int start = content.indexOf("录了本");
        int end = start + "录了本".length();
        spanString.setSpan(new RadiusBackgroundSpanString(radiusColor, 10), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        /*start = content.indexOf("enough");
        end = start + "enough".length();
        spanString.setSpan(new RadiusBackgroundSpan(radiusColor, 10), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        start = content.indexOf("important!");
        end = start + "important!".length();
        spanString.setSpan(new RadiusBackgroundSpan(radiusColor, 10), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        start = content.indexOf("Somewhere");
        end = start + "Somewhere".length();
        spanString.setSpan(new RadiusBackgroundSpan(radiusColor, 10), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        start = content.indexOf("himself");
        end = start + "himself".length();
        spanString.setSpan(new RadiusBackgroundSpan(radiusColor, 10), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);*/
        mText.append(spanString);


        int radiusColor1 = getResources().getColor(R.color.colorAccent);
        int radiusColor11 = getResources().getColor(R.color.pink);
        String content1 = getString(R.string.content1);
        SpannableString spanString1 = new SpannableString(content1);
        int start1 = content1.indexOf("录了本");
        int end1 = start1 + "录了本".length();
        spanString1.setSpan(new RadiusBgSpan(radiusColor1, radiusColor11, 10, STYLE_STROCK), start1, end1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mText11.append(spanString1);


        int radiusColor2 = getResources().getColor(R.color.colorAccent);
        int radiusColor22 = getResources().getColor(R.color.pink);
        String content2 = getString(R.string.content1);
        SpannableString spanString2 = new SpannableString(content2);
        int start2 = content2.indexOf("录了本");
        int end2 = start2 + "录了本".length();
        spanString2.setSpan(new RadiusBackgroundSpan(18, 20, 10, radiusColor22, radiusColor2), start2, end2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mText11.append(spanString2);

    }

    @Override
    public void setListener() {

    }
}
