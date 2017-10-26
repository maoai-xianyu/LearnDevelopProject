package com.mao.cn.learnDevelopProject.wedget.spannerString;

import android.app.Activity;

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

    public static void queryWordFromOnlineDictionary(String word, Activity activity, WordScanTranslateListener listener) {
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
                listener.wordTranslateSuccess(result);
            }

            @Override
            public void onError(TranslateErrorCode error) {
                listener.wordTranslateFail(error, null);
                //ToastUtils.show("查询错误:" + error.name());
            }
        });
    }

    public static void queryWordFromOfflineDictionary(String word, Activity activity, WordScanTranslateListener listener) {
        String pathU = "/Android/data/" + activity.getPackageName() + "/files/files/youdao/localdict/";
        EnWordTranslator.initDictPath(pathU);
        Translate translate = EnWordTranslator.lookupNative(word);
        if (translate == null) {
            listener.wordTranslateFail(null, "sorry,词丢了或者YouDaoApplication未初始化（未调用init）或者未授权");
            //ToastUtils.show("sorry,词丢了或者YouDaoApplication未初始化（未调用init）或者未授权");
            return;
        }
        listener.wordTranslateSuccess(translate);
    }

    public interface WordScanTranslateListener {
        void wordTranslateSuccess(Translate result);

        void wordTranslateFail(TranslateErrorCode error, String msg);
    }

}
