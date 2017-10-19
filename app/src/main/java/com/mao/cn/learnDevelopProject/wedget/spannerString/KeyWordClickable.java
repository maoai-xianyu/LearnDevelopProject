package com.mao.cn.learnDevelopProject.wedget.spannerString;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author： John
 * date：   15/9/24
 * e-mail： shijiao0471@163.com
 */
public class KeyWordClickable {

    public static final Pattern topicPattern = Pattern.compile("#[^#]*#");
    //public static final  Pattern topicPattern = Pattern.compile("#.+#");
    private static Context context1;
    private static String nodeName;

    public static void setKeyworldClickable(TextView textView, String ss, Pattern pattern, Context context) {
        Matcher matcher = pattern.matcher(ss);
        Map<String, Integer> temp;
        List<Map<String, Integer>> list = new ArrayList<>();
        int tempEnd = 0;
        String tempSS = ss;
        while (matcher.find()) {
            String key = matcher.group();
            LogU.i(" key " + key);
            if (!"".equals(key)) {
                int start = tempSS.indexOf(key);
                int end = start + key.length();
                LogU.i(" start " + start + " end " + end + " tempEnd " + tempEnd);
                tempSS = tempSS.substring(end);
                LogU.i(" tempSS " + tempSS);
                temp = new HashMap<>();
                temp.put("start", start + tempEnd);
                temp.put("end", end + tempEnd);
                list.add(temp);
                tempEnd = end + tempEnd;
            }
        }
        SpannableString spannableString = new SpannableString(ss);
        setClickTextView(textView, spannableString, list, context);
    }

    //跳转到原文界面
    private static void setClickTextView(final TextView textView, SpannableString ss, final List<Map<String, Integer>> list, final Context context) {
        WeifaClickSpan wcs = null;
        for (int i = 0; i < list.size(); i++) {
            wcs = new WeifaClickSpan(new OnTextviewClickListener() {
                @Override
                public void clickTextView() {
                    Toast.makeText(context, ss, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void setStyle(TextPaint ds) {
                    ds.setColor(Color.BLUE);
                    ds.setUnderlineText(false);
                }
            });

            ss.setSpan(wcs, list.get(i).get("start"), list.get(i).get("end"), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //跳转到 微法详情界面
    private static void setClickTextViewComment(final TextView textView, SpannableString ss, final List<Map<String, Integer>> list, final Context context, final ArrayList<String> lawId) {
        WeifaClickSpan wcs = null;
        context1 = context;

        for (int i = 0; i < list.size(); i++) {
            final String name = lawId.get(i);
            wcs = new WeifaClickSpan(new OnTextviewClickListener() {
                @Override
                public void clickTextView() {
                    String[] temName = name.split("#");
                    //Log.d("KeyWordClickable", temName[1]);
                    nodeName = temName[1];
//                    Common.getNodeList(context, mQueue, mHandler, temName[1]);
                    //跳转到微法详情 根据微法详情获取微法NodeId
                    /*Intent intent = new Intent(context, LawOriginalActivity.class);
                    intent.putExtra("NODE_NAME",name);
                    context.startActivity(intent);*/
                    //Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void setStyle(TextPaint ds) {
                    ds.setColor(Color.BLUE);
                    ds.setUnderlineText(false);
                }
            });

            ss.setSpan(wcs, list.get(i).get("start"), list.get(i).get("end"), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


}
