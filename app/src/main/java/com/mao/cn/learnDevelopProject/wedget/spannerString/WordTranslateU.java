package com.mao.cn.learnDevelopProject.wedget.spannerString;

import android.app.Activity;
import android.content.Intent;

import com.mao.cn.learnDevelopProject.model.TranslateData;
import com.mao.cn.learnDevelopProject.ui.activity.TranslateWordDetailActivity;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.TranslateErrorCode;
import com.youdao.sdk.ydonlinetranslate.TranslateListener;
import com.youdao.sdk.ydonlinetranslate.TranslateParameters;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.EnWordTranslator;
import com.youdao.sdk.ydtranslate.Translate;

/**
 * author:  zhangkun .
 * date:    on 2017/10/25.
 */

public class WordTranslateU {

    public static void queryWordFromOnlineDictory(String word, Activity activity) {
        // 源语言或者目标语言其中之一必须为中文,目前只支持中文与其他几个语种的互译

        Language langFrom = LanguageUtils.getLangByName("中文");
        // 若设置为自动，则查询自动识别源语言，自动识别不能保证完全正确，最好传源语言类型
        // Language langFrosm = LanguageUtils.getLangByName("自动");
        Language langTo = LanguageUtils.getLangByName("英文");
        TranslateParameters tps = new TranslateParameters.Builder()
                .source("youdao").from(langFrom).to(langTo).timeout(3000).build();// appkey可以省略

        Translator translator = Translator.getInstance(tps);

        translator.lookup(word, new TranslateListener() {

            @Override
            public void onResult(Translate result, String input) {
                TranslateData td = new TranslateData();
                td.setTranslate(result);
                Intent intent = new Intent(activity, TranslateWordDetailActivity.class);
                intent.putExtra("news", td);
                activity.startActivity(intent);
            }

            @Override
            public void onError(TranslateErrorCode error) {
                ToastUtils.show("查询错误:" + error.name());
            }
        });
    }

    public static void queryWordFromOfflineDictory(String word, Activity activity) {
        String pathU = "/Android/data/" + activity.getPackageName() + "/files/files/youdao/localdict/";
        EnWordTranslator.initDictPath(pathU);
        Translate translate = EnWordTranslator.lookupNative(word);
        if (translate == null) {
            ToastUtils.show("sorry,词丢了或者YouDaoApplication未初始化（未调用init）或者未授权");
            return;
        }
        TranslateData td = new TranslateData();
        td.setTranslate(translate);
        Intent intent = new Intent(activity, TranslateWordDetailActivity.class);
        intent.putExtra("news", td);
        activity.startActivity(intent);
    }

}
