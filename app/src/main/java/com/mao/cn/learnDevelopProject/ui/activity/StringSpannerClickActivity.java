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

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerStringSpannerClickComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.model.TransLateBaiDuDetail;
import com.mao.cn.learnDevelopProject.model.TranslateData;
import com.mao.cn.learnDevelopProject.modules.StringSpannerClickModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IStringSpannerClick;
import com.mao.cn.learnDevelopProject.ui.presenter.StringSpannerClickPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.FileU;
import com.mao.cn.learnDevelopProject.utils.tools.FileUtils;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.PathU;
import com.mao.cn.learnDevelopProject.wedget.spannerString.AnnotationTextView;
import com.mao.cn.learnDevelopProject.wedget.spannerString.ClickableColorSpan;
import com.mao.cn.learnDevelopProject.wedget.spannerString.KeyWordClickable;
import com.mao.cn.learnDevelopProject.wedget.spannerString.SPAnnotationTextView;
import com.mao.cn.learnDevelopProject.wedget.spannerString.WordResuorceU;
import com.mao.cn.learnDevelopProject.wedget.spannerString.WordTranslateU;
import com.youdao.sdk.ydonlinetranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.EnWordTranslator;
import com.youdao.sdk.ydtranslate.Translate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class StringSpannerClickActivity extends BaseActivity implements IStringSpannerClick {

    @Inject
    StringSpannerClickPresenter presenter;

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_show_content)
    TextView tvShowContent;
    @BindView(R.id.atv_content)
    AnnotationTextView atvContent;
    @BindView(R.id.sp_atv_content)
    SPAnnotationTextView spAtvContent;
    @BindView(R.id.sp_atv_content_point)
    SPAnnotationTextView spAtvContentPoint;
    @BindView(R.id.sp_atv_content_point_native)
    SPAnnotationTextView spAtvContentPointNative;
    @BindView(R.id.sp_atv_content_point_baidu)
    SPAnnotationTextView spAtvContentPointBaidu;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_stringspanner_click;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("查单词");
        tvHeaderTitle.setVisibility(View.VISIBLE);

        String str = "Hey, look! Shooting stars[^1].";
        String strNoAnn = "Hey, look! Shooting stars.";
        String strNoAnns = "Hey, look! Shooting stars[^1]. Oh, oh. Quick, quick.Make a wish. Make a wish.You gotta[^2] make a wish.";

        String strAll = "Hey, look! Shooting stars[^1].\nOh, oh. Quick, quick. Make a wish. Make a wish. You gotta[^2] make a wish. \nWow, my wish came true. \nI'm okay.\nMine too.\n[^1]流星雨\n[^2]必须（gotta等于got to, 是口语化表达）";

        String strLong = "Switzerland's plan for dealing with a potential invasion from anybody was called \"The National Redoubt\" and it was essentially intended to make any possible enemy look at Switzerland and think to themselves: No thanks, I'll take over something a little easier instead.\n" +
                "\n" +
                "The first reason why is Switzerland continues to have mandatory male conscription, meaning that all men have to serve in the military for 170 days and receive basic training, and it has the ability to mobilize over 200,000 soldiers within 72 hours of an emergency being declared.\n" +
                "\n" +
                "In addition, every road, bridge, tunnel and railroad in the country has been designed in a way that they can be remotely blown up to deny a possible invader from ever using them, including entire mountain sides that can be detonated to cause a landslide to block off entire roads.\n" +
                "\n" +
                "High in the mountains, the Swiss have built over 26,000 bunkers and fortified positions ranging from anti-tank guns anti-air guns or machine gun nests, which means that the entire country is basically one big booby trap — an impregnable castle in the center of Europe.";
        atvContent.setAnnotationText(strNoAnns, WordResuorceU.getAnnotation(strAll));

        spAtvContent.setAnnotationText(strNoAnn, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                WordTranslateU.queryWordFromOnlineDictionary(word, StringSpannerClickActivity.this, new WordTranslateU.WordScanTranslateListener() {
                    @Override
                    public void wordTranslateSuccess(Translate result) {
                        scanWordSuccess(result);
                    }

                    @Override
                    public void wordTranslateFail(TranslateErrorCode error, String msg) {

                    }
                });

            }
        });

        spAtvContentPoint.setAnnotationText(strNoAnns, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                WordTranslateU.queryWordFromOnlineDictionary(word, StringSpannerClickActivity.this, new WordTranslateU.WordScanTranslateListener() {
                    @Override
                    public void wordTranslateSuccess(Translate result) {
                        scanWordSuccess(result);
                    }

                    @Override
                    public void wordTranslateFail(TranslateErrorCode error, String msg) {

                    }
                });
            }
        });

        spAtvContentPointNative.setAnnotationText(strNoAnns, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                // 离线的是否需要除东西
                WordTranslateU.queryWordFromOfflineDictionary(word.toLowerCase(), StringSpannerClickActivity.this, new WordTranslateU.WordScanTranslateListener() {
                    @Override
                    public void wordTranslateSuccess(Translate result) {
                        scanWordSuccess(result);
                    }

                    @Override
                    public void wordTranslateFail(TranslateErrorCode error, String msg) {

                    }
                });
            }
        });

        spAtvContentPointBaidu.setAnnotationText(strNoAnn, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                presenter.getWordTranslate(word, "auto", "auto");
            }
        });


        String sdPath = PathU.getInstance().getAssetsFile() + "/youdao/localdict/localdict.datx";
        String pathU = "/Android/data/" + context.getPackageName() + "/files/files/youdao/localdict";
        if (FileU.isExist(sdPath)) {
            EnWordTranslator.initDictPath(pathU + "/");
            if (!EnWordTranslator.isInited()) {
                EnWordTranslator.init();
            }
        } else {
            FileUtils.getInstance(this).copyAssetsToSD("youdao/localdict", pathU).setFileOperateCallback(new FileUtils.FileOperateCallback() {
                @Override
                public void onSuccess() {
                    LogU.i(" 文件复制成功时，主线程回调 ");
                    EnWordTranslator.initDictPath(pathU + "/");
                    if (!EnWordTranslator.isInited()) {
                        EnWordTranslator.init();
                    }
                }

                @Override
                public void onFailed(String error) {
                    LogU.e(" 文件复制失败时，主线程回调 ");
                }
            });
        }

        //getData();
        //tvShowContent.setText(getClickableSpan());
        //tvShowContent.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
    }

    private void scanWordSuccess(Translate result) {
        TranslateData td = new TranslateData();
        td.setTranslate(result);
        Intent intent = new Intent(activity, TranslateWordDetailActivity.class);
        intent.putExtra("news", td);
        activity.startActivity(intent);
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

    @Override
    public void showWordTransLate(List<TransLateBaiDuDetail> trans_result) {
        if (!checkActivityState()) return;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < trans_result.size(); i++) {
            if (i == 0) {
                builder.append(trans_result.get(i));
            } else {
                builder.append(", ").append(trans_result.get(i));
            }
        }
        onTip("翻译为：" + builder.toString());

    }
}
