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
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerStringSpannerClickComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.model.TransLateBaiDuDetail;
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
import com.youdao.sdk.ydtranslate.EnWordTranslator;

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

        atvContent.setAnnotationText(strNoAnns, WordResuorceU.getAnnotation(strAll));

        spAtvContent.setAnnotationText(strNoAnn, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                WordTranslateU.queryWordFromOnlineDictory(word, StringSpannerClickActivity.this);

            }
        });

        spAtvContentPoint.setAnnotationText(strNoAnns, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                WordTranslateU.queryWordFromOnlineDictory(word, StringSpannerClickActivity.this);
            }
        });

        spAtvContentPointNative.setAnnotationText(strNoAnns, WordResuorceU.getAnnotation(strAll), new SPAnnotationTextView.ClickWordListener() {
            @Override
            public void showClickContent(String word) {
                WordTranslateU.queryWordFromOfflineDictory(word.toLowerCase(), StringSpannerClickActivity.this);
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
