package com.mao.cn.learnDevelopProject.model;

import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.WebExplain;

import java.io.Serializable;
import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2017/10/25.
 */

public class TranslateData implements Serializable {


    private Translate translate;

    public Translate getTranslate() {
        return translate;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }

    public String means() {
        return listStr(translate.getExplains());
    }

    public String translates() {
        return listStr(translate.getTranslations());
    }

    public String getQuery(){
        return translate.getQuery();
    }

    public String webMeans() {
        StringBuilder sb = new StringBuilder();

        List<WebExplain> explains = translate.getWebExplains();

        if (explains != null) {
            for (WebExplain s : explains) {
                sb.append(s.getKey()).append(":").append(listStr(s.getMeans())).append("\n");
            }
        }

        return sb.toString();
    }

    private String listStr(List<String> list) {
        StringBuilder sb = new StringBuilder();

        if (list != null) {
            for (String s : list) {
                sb.append(s).append("\n");
            }
        }

        return sb.toString();
    }
}
