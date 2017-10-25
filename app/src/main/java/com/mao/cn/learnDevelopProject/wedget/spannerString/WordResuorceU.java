package com.mao.cn.learnDevelopProject.wedget.spannerString;

import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;

import com.mao.cn.learnDevelopProject.contants.KeyMaps;
import com.mao.cn.learnDevelopProject.utils.tools.NumberU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author:  zhangkun .
 * date:    on 2017/10/24.
 */

public class WordResuorceU {

    /**
     * 获取所有的注释编号
     *
     * @param text
     * @return
     */
    public static List<Integer> getAnnotationNum(String text) {
        List<Integer> annotationNumList = new ArrayList<>();
        if (StringU.isNotEmpty(text)) {
            String patternStr = KeyMaps.REGEX.multiline_regex + KeyMaps.REGEX.foot_node_inline_regex;
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                annotationNumList.add(NumberU.toInt(matcher.group(1)));
            }
        }
        return annotationNumList;
    }

    public static Spanned getStringByHtml(String string) {
        return Html.fromHtml(string);
    }

    /**
     * 获取所有的注释内容
     *
     * @param content
     * @return
     */
    public static SparseArray<String> getAnnotation(String content) {
        SparseArray<String> sparseArray = new SparseArray<>();
        String str = KeyMaps.REGEX.multiline_regex + KeyMaps.REGEX.foot_node_ref_regex;
        if (StringU.isNotEmpty(content)) {
            String[] sentenceArray = content.split("\n");
            for (String sentence : sentenceArray) {
                Pattern pattern = Pattern.compile(str);
                Matcher matcher = pattern.matcher(sentence);
                while (matcher.find()) {
                    sparseArray.put(NumberU.toInt(matcher.group(1)), matcher.group(2));
                }
            }
        }
        return sparseArray;
    }
}
