package com.mao.cn.learnDevelopProject.ui.activity;

/**
 * @(#)BrowserActivity.java, 2013�?10�?22�?. Copyright 2013 Yodao, Inc. All
 * rights reserved. YODAO PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.model.TranslateData;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;
import com.youdao.sdk.ydtranslate.Translate;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author lukun
 */
public class TranslateDetailActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.input)
    TextView input;
    @BindView(R.id.translation)
    TextView translation;
    @BindView(R.id.spell)
    TextView spell;
    @BindView(R.id.ukSpell)
    TextView ukSpell;
    @BindView(R.id.usSpell)
    TextView usSpell;
    @BindView(R.id.means)
    TextView means;
    @BindView(R.id.webmeans)
    TextView webmeans;
    @BindView(R.id.moreBtn)
    TextView moreBtn;

    TranslateData translateData;
    Translate translate;

    @Override
    public void getArgs(Bundle bundle) {
        if (bundle != null) {
            translateData = (TranslateData) this.getIntent().getSerializableExtra("news");
        }

    }

    @Override
    public int setView() {
        return R.layout.aty_translate_detail;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("单词详情");
        tvHeaderTitle.setVisibility(View.VISIBLE);
        setResult(Activity.RESULT_OK);
        initData();
    }

    private void initData() {
        if (translateData != null) {
            translate = translateData.getTranslate();
            if (StringU.isNotEmpty(translate.getQuery())) {
                input.setText("输入：" + translate.getQuery());
            }

            if (StringU.isNotEmpty(translateData.translates())) {
                translation.setText("结果：" + translateData.translates());
            }

            if (StringU.isNotEmpty(translate.getPhonetic())) {
                spell.setText("发音：" + translate.getPhonetic());
            }

            if (StringU.isNotEmpty(translate.getUkPhonetic())) {
                ukSpell.setText("英式发音：" + translate.getUkPhonetic());
            }

            if (StringU.isNotEmpty(translate.getUsPhonetic())) {
                usSpell.setText("美式发音：" + translate.getUsPhonetic());
            }

            if (StringU.isNotEmpty(translateData.means())) {
                means.setText("翻译结果：\n" + translateData.means());
            }

            if (StringU.isNotEmpty(translateData.webMeans())) {
                webmeans.setText("网络释义：\n" + translateData.webMeans());
            }

            moreBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    translate.openMore(TranslateDetailActivity.this);
//                //注意，若用户没安装有道词典，开发者可自己实现deeplink的跳转
//                if(!trs.openDict(TranslateDetailActivity.this)){
//                	 //获取deeplink链接
//                    String deeplinkUrl = trs.getDictWebUrl();
//                    //处理deeplink链接，可通过自定义浏览器打开
//                TranslateForwardHelper.toBrowser(TranslateDetailActivity.this, deeplinkUrl);
//                }
                }
            });

        }

    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    public void loginBack(View view) {
        this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void finish() {
        super.finish();
    }

}
