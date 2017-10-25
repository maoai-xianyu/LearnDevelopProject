package com.mao.cn.learnDevelopProject.wedget.spannerString;

// +----------------------------------------------------------------------
// | CreateTime: 16/9/19 
// +----------------------------------------------------------------------
// | Author:     zhangkun
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
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
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

import java.util.List;


public class AnnotationTextView extends TextView {
    public static final String ANNOTATION_LEFT = "[^";
    public static final String ANNOTATION_RIGHT = "]";
    private float mTouchX, mTouchY;

    public AnnotationTextView(Context context) {
        super(context);
    }

    public AnnotationTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnnotationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAnnotationText(String text, SparseArray<String> annotations) {
        setAnnotationText(text, annotations, null);
    }

    public void setAnnotationText(String text, SparseArray<String> annotations, OnClickAnnotationListener listener) {
        if (StringU.isNotEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            setAnnotationText(builder, annotations, listener);
        }
    }

    public void setAnnotationText(SpannableStringBuilder builder, SparseArray<String> annotations, OnClickAnnotationListener listener) {
        String keyword;
        int start, end;
        int startNum = 0;

        String text = builder.toString();
        List<Integer> annotationNumList = WordResuorceU.getAnnotationNum(text);
        if (ListU.notEmpty(annotationNumList)) { // 内容中存在注释
            startNum = annotationNumList.get(0); // 找到第一个注释的编号
            for (int i = startNum; i - startNum < annotationNumList.size(); i++) {
                keyword = ANNOTATION_LEFT + i + ANNOTATION_RIGHT;
                start = StringU.indexOf(text, keyword);
                if (start != -1) {
                    end = start + keyword.length();
                    if (start >= 0 && end <= text.length()) {
                        builder.setSpan(new ImageSpan(LearnDevelopApplication.context(), R.drawable.icon_annotation),
                                start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        String annotation;
                        if (annotations != null && annotations.size() > 0 && i - startNum < annotations.size()) {
                            annotation = annotations.get(i);
                        } else {
                            annotation = LearnDevelopApplication.context().getResources().getString(R.string.no_annotation);
                        }
                        int annotationNumber = i;
                        builder.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                if (listener != null) {
                                    listener.onClickAnnotation(annotationNumber);
                                    return;
                                }
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
            setMovementMethod(LinkMovementMethod.getInstance());
        }
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

    public interface OnClickAnnotationListener {
        void onClickAnnotation(int annotationNumber);
    }
}
