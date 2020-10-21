package com.mao.cn.learnDevelopProject.ui.activity.tabhost.fragment;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBackgroundSpan;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBackgroundSpanString;
import com.mao.cn.learnDevelopProject.ui.activity.tabhost.RadiusBgSpan;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import org.jetbrains.annotations.NotNull;

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
    @BindView(R.id.tvDest11111111)
    TextView tvDest11111111;
    @BindView(R.id.tvDestM)
    TextView tvDestM;

    private String originKeyword = "dadi";

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
        spanString2.setSpan(new RadiusBackgroundSpan(11, 80, 10, radiusColor22, radiusColor2, STYLE_STROCK),
                start2, end2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvDest11111111.append(spanString2);

        tvDestM.setText(changeHigherTextColor("蚁人"));
        tvDestM.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void setListener() {

    }


    private SpannableString changeHigherTextColor(String correction) {
        String text = getString(R.string.movie_search_correction, correction, originKeyword);
        String firstText = getString(R.string.movie_search_correction_first);
        text = firstText + text;
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.hex_f03d37)), firstText.length(),
                firstText.length() + correction.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NotNull View widget) {
                if (getActivity() != null) {
                    ToastUtils.show("已为您智能拼音搜索");
                }
            }

            @Override
            public void updateDrawState(@NotNull TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.hex_f03d37));
                ds.setUnderlineText(false);
            }
        }, text.length() - originKeyword.length() - 1, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RadiusBackgroundSpan(11, 80, 25,
                        getResources().getColor(R.color.hex_f03d37), getResources().getColor(R.color.hex_1Af03d37)),
                text.length() - originKeyword.length() - 1, text.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
