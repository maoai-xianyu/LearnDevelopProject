package com.mao.cn.learnDevelopProject.wedget.spannerString;

// +----------------------------------------------------------------------
// | CreateTime: 17/10/20 
// +----------------------------------------------------------------------
// | Author:     fujiuhong
// +----------------------------------------------------------------------
// | Description:  
// +----------------------------------------------------------------------

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class WordTextView extends AppCompatTextView {
    Context context;
    public WordTextView(Context context) {
        super(context);
        this.context=context;
    }

    public WordTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public WordTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public void setTextValue(String str){
        this.setText(str, BufferType.SPANNABLE);
        getEachWord(this);
        this.setMovementMethod(LinkMovementMethod.getInstance());
    }



    public void getEachWord(TextView textView) {
        Spannable spans = (Spannable) textView.getText();
        Integer[] indices = getIndices(
                textView.getText().toString().trim(), ' ');
        int start = 0;
        int end = 0;
        // to cater last/only word loop will run equal to the length of indices.length
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = getClickableSpan();
            // to cater last/only word
            end = i < indices.length ? indices[i] : spans.length();
            spans.setSpan(clickSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }
        //改变选中文本的高亮颜色
        textView.setHighlightColor(Color.BLUE);
    }

    private ClickableSpan getClickableSpan() {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                String s = tv.getText().subSequence(tv.getSelectionStart(),
                                tv.getSelectionEnd()).toString();
                s = s.replace(" ", "");
                if (s == null || s.equals("")) {
                    return;
                }
                s = StringFilter(s);
                Log.d("tapped on:", s);
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }



            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }


    public static String StringFilter(String str) throws PatternSyntaxException {
// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
// 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}


