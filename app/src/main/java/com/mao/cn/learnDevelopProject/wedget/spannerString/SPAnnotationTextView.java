package com.mao.cn.learnDevelopProject.wedget.spannerString;

// +----------------------------------------------------------------------
// | CreateTime: 16/9/19 
// +----------------------------------------------------------------------
// | Author:     zhangkun
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.LearnDevelopApplication;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SPAnnotationTextView extends TextView {
    public static final String ANNOTATION_LEFT = "[^";
    public static final String ANNOTATION_RIGHT = "]";
    private float mTouchX, mTouchY;

    public static final Pattern topicPattern = Pattern.compile("[a-zA-Z]+[-']?[a-zA-Z]*");

    public SPAnnotationTextView(Context context) {
        super(context);
    }

    public SPAnnotationTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SPAnnotationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAnnotationText(String text, SparseArray<String> annotations, ClickWordListener listener) {
        if (StringU.isNotEmpty(text)) {
            setAnnotationTextSSB(text, annotations, listener);
        }
    }

    /**
     * 检测文本中的注释
     *
     * @param str
     * @param annotations
     */
    public void setAnnotationTextSSB(String str, SparseArray<String> annotations, ClickWordListener listener) {
        String keyword;
        int start, end;
        int startNum = 0;

        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        List<Integer> annotationNumList = WordResuorceU.getAnnotationNum(str);
        if (ListU.notEmpty(annotationNumList)) { // 内容中存在注释
            startNum = annotationNumList.get(0); // 找到第一个注释的编号
            for (int i = startNum; i - startNum < annotationNumList.size(); i++) {
                keyword = ANNOTATION_LEFT + i + ANNOTATION_RIGHT;
                start = StringU.indexOf(str, keyword);
                if (start != -1) {
                    end = start + keyword.length();
                    if (start >= 0 && end <= str.length()) {
                        builder.setSpan(new ImageSpan(LearnDevelopApplication.context(), R.drawable.icon_annotation),
                                start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        String annotation;
                        if (annotations != null && annotations.size() > 0 && i - startNum < annotations.size()) {
                            annotation = annotations.get(i);
                        } else {
                            annotation = LearnDevelopApplication.context().getResources().getString(R.string.no_annotation);
                        }
                        builder.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                View contentView = LayoutInflater.from(LearnDevelopApplication.context()).inflate(R.layout.pop_annotation, null);
                                TextView tvAnnotation = (TextView) contentView.findViewById(R.id.tv_annotation);
                                ScrollView svAnnotation = (ScrollView) contentView.findViewById(R.id.sv_annotation);
                                if (tvAnnotation != null) {
                                    tvAnnotation.setText(WordResuorceU.getStringByHtml(annotation));
                                    int width = LearnDevelopApplication.getScreenWidth();
                                    int padding = 10;
                                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(svAnnotation.getLayoutParams());
                                    if (mTouchX <= width / 2) { // 点击屏幕左边,显示在右边
                                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                                        layoutParams.setMargins((int) mTouchX, (int) mTouchY, padding, padding);
                                        svAnnotation.setLayoutParams(layoutParams);
                                    } else { // 点击屏幕右边,显示在左边
                                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                                        layoutParams.setMargins(padding, (int) mTouchY, width - (int) mTouchX, padding);
                                        svAnnotation.setLayoutParams(layoutParams);
                                    }

                                    PopupWindow popupWindow = new PopupWindow(contentView, RelativeLayout.LayoutParams.MATCH_PARENT,
                                            RelativeLayout.LayoutParams.MATCH_PARENT, true);
                                    popupWindow.setOutsideTouchable(true);
                                    popupWindow.setTouchable(true);
                                    contentView.setOnClickListener(v -> {
                                        if (popupWindow.isShowing()) {
                                            popupWindow.dismiss();
                                        }
                                    });
                                    popupWindow.showAtLocation(widget, Gravity.NO_GRAVITY, 0, 0);
                                }
                            }
                        }, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
            }
        }
        builder = setKeyWordClickable(builder, topicPattern, listener);
        setMovementMethod(LinkMovementMethod.getInstance());
        setText(builder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getRawX();
                mTouchY = event.getRawY();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 用正则表达式匹配对应的单词
     *
     * @param ssBuilder
     * @param pattern
     * @return
     */
    public static SpannableStringBuilder setKeyWordClickable(SpannableStringBuilder ssBuilder, Pattern pattern, ClickWordListener listener) {
        String tempSSBuilder = ssBuilder.toString();
        LogU.i("  ss.toString()) " + tempSSBuilder);
        Matcher matcher = pattern.matcher(tempSSBuilder);
        Map<String, Integer> temp;
        List<Map<String, Integer>> list = new ArrayList<>();
        int tempEnd = 0;
        while (matcher.find()) {
            String key = matcher.group();
            LogU.i(" key " + key);
            if (!"".equals(key)) {
                int start = tempSSBuilder.indexOf(key);
                int end = start + key.length();
                LogU.i(" start " + start + " end " + end + " tempEnd " + tempEnd);
                tempSSBuilder = tempSSBuilder.substring(end);
                LogU.i(" tempSS " + tempSSBuilder);
                temp = new HashMap<>();
                temp.put("start", start + tempEnd);
                temp.put("end", end + tempEnd);
                list.add(temp);
                tempEnd = end + tempEnd;
            }
        }
        return setClickTextView(ssBuilder, list, listener);
    }

    /**
     * 给对应的单词进行设置点击事件
     *
     * @param ss
     * @param list
     * @return
     */
    private static SpannableStringBuilder setClickTextView(SpannableStringBuilder ss, final List<Map<String, Integer>> list, ClickWordListener listener) {
        for (int i = 0; i < list.size(); i++) {
            ClickableSpan wcs = clickWordToDictionary(listener);
            ss.setSpan(wcs, list.get(i).get("start"), list.get(i).get("end"), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    /**
     * 点击之后去查字典
     *
     * @return
     */
    private static ClickableSpan clickWordToDictionary(ClickWordListener listener) {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                String word = tv.getText().subSequence(tv.getSelectionStart(), tv.getSelectionEnd()).toString();
                LogU.i("点击的值是 " + word);
                listener.showClickContent(word);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    public interface ClickWordListener {
        void showClickContent(String word);
    }
}
