package com.mao.cn.learnDevelopProject.ui.activity;

/**
 * zkangkun
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.KeyMaps;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;


public class HtmlActivity extends BaseActivity {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ll_content)
    LinearLayout activityCommentCourse;

    ProgressBar progressBar;
    @BindView(R.id.fl_content)
    FrameLayout frameLayout;

    private String htmlUrl;
    private int htmlType = 0;

    public static final int HANDLER_BUY_MEMBER = 1;
    private static final int BUY_COMBOS = 2;
    private static final int SHARE_WITH_FRIENDS = 3;
    private static final int KINDERGARTEN_BUY = 4;

    private static final int BUY_INTERNATIONAL_CLASS = 6;
    private static final int BUY_MICRO_LESSON = 7;
    private static final int BUY_NEW_MEMBER = 8;


    MyHandler jsHandler;
    boolean isOnPageFinished;

    @Override
    public void getArgs(Bundle bundle) {
        if (bundle != null) {
            htmlType = bundle.getInt(KeyMaps.HTML_TYPE);
        }

    }

    @Override
    public int setView() {
        return R.layout.aty_html;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        setResult(RESULT_OK);
        jsHandler = new MyHandler(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        webView.getSettings().setJavaScriptEnabled(true);//支持js
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持js打开新窗口
        webView.setWebChromeClient(new WebClient());
        webView.setWebViewClient(new ClassWebView());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.addJavascriptInterface(new HtmlApp(), "android");
        webView.requestFocus();
        switch (htmlType) {
            case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_COMMENT:
                tvHeaderTitle.setText("购买");
                htmlUrl = "https://www.boxfish.cn/FAQ/detail/ForeignComment.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_PUBLIC_CLASS:
                tvHeaderTitle.setText("购买");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/publicClass/index3.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_INTELLIGENT_CLASS:
                tvHeaderTitle.setText("购买");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/correct/index.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_TRAINING_CN_CLASS:
                tvHeaderTitle.setText("购买");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/answer/index2.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_INVITING_FRIENDS:
                tvHeaderTitle.setText("购买");
                if (htmlUrl == null) {
                    htmlUrl = "https://www.boxfish.cn/boxfish-class/recommend/index.html";
                }
                break;
            case KeyMaps.HtmlType.HTML_TYPE_BUY_COURSE_REWARD_RULE:
                ibHeaderBack.setVisibility(View.GONE);
                tvHeaderTitle.setText("购买");
                if (htmlUrl == null) {
                    htmlUrl = "https://www.boxfish.cn/boxfish-class/regulation/index.html";
                }
                break;
            case KeyMaps.HtmlType.HTML_TYPE_LEVEL:
                tvHeaderTitle.setText("购买");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/levelIntroduction/index.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_BOXFISH_MORE_BETTER_THAN_NEWEAST:
                tvHeaderTitle.setText("购买");
                if (htmlUrl == null) {
                    htmlUrl = "https://www.boxfish.cn/amazing/detail/difference/difference1.html?parentShare=1";
                }
                htmlUrl = htmlUrl + "&bonus=0";
                break;
            //kg学员购买详情
            case KeyMaps.HtmlType.HTML_TYPE_K_MEMBER_BUY_DETAIL:
                tvHeaderTitle.setText("购买");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/buy/index.html?shareable=1";
                break;
            //kg的外教大讲堂
            case KeyMaps.HtmlType.HTML_TYPE_K_PUBLIC_CLASS_KINDERGARTEN:
                tvHeaderTitle.setText("测试");
                tvHeaderRight.setText("购买");
                tvHeaderRight.setVisibility(View.VISIBLE);
                setHeaderRight(tvHeaderRight);
                if (htmlUrl == null) {
                    htmlUrl = "https://www.boxfish.cn/boxfish-class/publicClass/index-kindergarten.html";
                }
                break;
            //kg教学理念
            case KeyMaps.HtmlType.HTML_TYPE_K_PLANNING_GOAL:
                tvHeaderTitle.setText("测试");
                tvHeaderRight.setText("购买");
                tvHeaderRight.setVisibility(View.VISIBLE);
                setHeaderRight(tvHeaderRight);
                if (htmlUrl == null) {
                    htmlUrl = "https://www.boxfish.cn/boxfish-class/bilingual/index.html";
                }
                break;
            //boxfish国际线上学校
            case KeyMaps.HtmlType.HTML_TYPE_MEMBER_DETAIL:
                tvHeaderTitle.setText("测试");
                tvHeaderRight.setText("购买");
                tvHeaderRight.setVisibility(View.VISIBLE);
                setHeaderRight(tvHeaderRight);
                htmlUrl = "https://www.boxfish.cn/boxfish-class/onlineSchool/index.html";
                break;
            //外教口语指导
            case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_FUDAO:
                tvHeaderTitle.setText("测试");
                htmlUrl = "https://www.boxfish.cn/activity/membership/index3.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_MEMBER_EVALUATION:

                break;
            case KeyMaps.HtmlType.HTML_TYPE_VIP_ITEM_2:
                htmlUrl = "https://www.boxfish.cn/activity/medal/index2.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_VIP_ITEM_6:
                htmlUrl = "https://www.boxfish.cn/activity/ten/ten5.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_VIP_ITEM_9:
                htmlUrl = "https://www.boxfish.cn/boxfish-class/courses/index.html?from=groupmessage&isappinstalled=0";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_VIP_ITEM_10:
                htmlUrl = "https://www.boxfish.cn/activity/learn-together/index2.html";
                break;

            //国际版页面知识点外教强化练习链接
            case KeyMaps.HtmlType.HTML_TYPE_HTML_MICRO:
                htmlUrl = "http://www.boxfish.cn/amazing/detail/knowledge?parentShare=1";
                break;
            //购买页跳转过来的定制学习规划
            case KeyMaps.HtmlType.HTML_TYPE_ALL_BUY:
                tvHeaderTitle.setText("定制学习规划");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/buyChoice/index.html";
                break;
            //购买微课
            case KeyMaps.HtmlType.HTML_TYPE_MICRO_LESSON_BUY:
                tvHeaderTitle.setText("知识点外教强化练习");
                findViewById(R.id.ll_bug_group).setVisibility(View.VISIBLE);
                htmlUrl = "https://www.boxfish.cn/boxfish-class/learnNew/index.html";
                break;

            //购买微课的购买须知
            case KeyMaps.HtmlType.HTML_TYPE_MICRO_MIANZE:
                tvHeaderTitle.setText("BOXFiS用户购买条款");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/purchaseTerms/index.html";
                break;

            //购买国际版下线网页
            case KeyMaps.HtmlType.HTML_TYPE_MEMBER_PRODUCT_OFFLINE:
                tvHeaderTitle.setText("学习计划");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/learnPlan/learnPlan2.html";

                break;
            //购买微课的下线网页
            case KeyMaps.HtmlType.HTML_TYPE_MICRO_OFFLINE:
                tvHeaderTitle.setText("学习计划");
                htmlUrl = "https://www.boxfish.cn/boxfish-class/learnPlan/learnPlan1.html";
                break;
            //外教一对一翻转课堂
            case KeyMaps.HtmlType.HTML_TYPE_ONE_TO_ONE_DETAIL:
                htmlUrl = "https://www.boxfish.cn/boxfish-class/onlineSchool/index-new.html";
                break;

            //外交强化详情
            case KeyMaps.HtmlType.HTML_TYPE_MICRO_LESSON_DETAIL:
                htmlUrl = "https://www.boxfish.cn/boxfish-class/learnNew/index.html";
                break;
            case KeyMaps.HtmlType.HTML_TYPE_ORAL_TEST:
                tvHeaderTitle.setText("测试");
                htmlUrl = "https://www.boxfish.cn/activity/nine/index.html";
                htmlUrl = htmlUrl + "?bonus=0";
                setRightTitle("购买培优课");
                break;
            //大师课
            case KeyMaps.HtmlType.HTML_TYPE_MASTER_CLASS:
                htmlUrl = "https://www.boxfish.cn/inner_app/masterclassintro";
                setRightTitle("购买培优课");
                break;
            //公益课
            case KeyMaps.HtmlType.HTML_TYPE_CHINESE_PUBLICE_CLASS:
                htmlUrl = "https://www.boxfish.cn/inner_app/promotionclassintro";
//                setRightTitle("成为付费学员");
                break;
            //中教名师课
            case KeyMaps.HtmlType.HTML_TYPE_CHINESE_TEACHER_CLASS:
                htmlUrl = "https://www.boxfish.cn/inner_app/publicclasscnintro";
                setRightTitle("购买培优课");
                break;
            //外教明师课
            case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_CLASS:
                htmlUrl = "https://www.boxfish.cn/inner_app/publicclassfnintro";
                setRightTitle("购买培优课");
                break;
            //购买外教培优课介绍网页181109
            case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_LESSON_DETAIL:
                htmlUrl = "https://www.boxfish.cn/inner_app/v12/intro1.html";
                setRightTitle("购买培优课");
                break;
            //购买国际班介绍网页181109
            case KeyMaps.HtmlType.HTML_TYPE_INTERNATION_LESSON_DETAIL:
                htmlUrl = "https://www.boxfish.cn/inner_app/v12/intro2.html";
                setRightTitle("购买国际班");
                break;
            case KeyMaps.HtmlType.HTML_TYPE_REVOLUTION:
                htmlUrl = "https://www.boxfish.cn/share/market/revolution.html?from=groupmessage&isappinstalled=0";
                setRightTitle("购买培优课");
                break;
            default:
                tvHeaderTitle.setText("html5");
                break;
        }

        webView.loadUrl(htmlUrl);
    }

    private void setRightTitle(String rightText) {
        tvHeaderRight.setText(rightText);
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setTextColor(getResources().getColor(R.color.c_f5b100));
    }

    public void setHeaderRight(TextView tvHeaderRight) {
        tvHeaderRight.setTextColor(getResources().getColor(R.color.c_000000));
        tvHeaderRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_12));
        tvHeaderRight.setBackgroundResource(R.drawable.shape_corner_button);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tvHeaderRight.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.d5);
        tvHeaderRight.setLayoutParams(layoutParams);
        tvHeaderRight.setPadding((int) getResources().getDimension(R.dimen.d10), (int) getResources().getDimension(R.dimen.d5),
                (int) getResources().getDimension(R.dimen.d10), (int) getResources().getDimension(R.dimen.d5));
    }

    @Override
    public void setListener() {


        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS).subscribe(aVoid -> {
            onBackPressed();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(tvHeaderRight).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS).subscribe(aVoid -> {
            switch (htmlType) {
                case KeyMaps.HtmlType.HTML_TYPE_K_PLANNING_GOAL:
                    break;
                case KeyMaps.HtmlType.HTML_TYPE_MEMBER_DETAIL:
                    break;
                case KeyMaps.HtmlType.HTML_TYPE_K_PUBLIC_CLASS_KINDERGARTEN:
                    break;
                case KeyMaps.HtmlType.HTML_TYPE_MASTER_CLASS:
                case KeyMaps.HtmlType.HTML_TYPE_CHINESE_TEACHER_CLASS:
                case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_CLASS:
                case KeyMaps.HtmlType.HTML_TYPE_ORAL_TEST:
                case KeyMaps.HtmlType.HTML_TYPE_REVOLUTION:
                    startToBuyActivity(activity, KeyMaps.InternationalMember.HTML_TYPE_FOREIGN_TEACHER_LESSONS);
                    break;
                case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_LESSON_DETAIL:
                    startToBuyActivity(activity, KeyMaps.InternationalMember.HTML_TYPE_FOREIGN_TEACHER_LESSONS);
                    break;
                case KeyMaps.HtmlType.HTML_TYPE_INTERNATION_LESSON_DETAIL:
                    startToBuyActivity(activity, KeyMaps.InternationalMember.HTML_TYPE_INTERNATION_LESSONS);
                    break;


            }

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    public static void startToBuyActivity(Activity activity, String memberState) {
        Intent intent = null;
        Bundle bundle = new Bundle();
        switch (memberState) {
            //购买外教培优课
            case KeyMaps.InternationalMember.HTML_TYPE_FOREIGN_TEACHER_LESSONS:
                intent = new Intent(activity, HtmlActivity.class);
                bundle.putInt(KeyMaps.HTML_TYPE, KeyMaps.HtmlType.HTML_TYPE_INTERNATION_LESSON_DETAIL);
                break;
            //购买国际班
            case KeyMaps.InternationalMember.HTML_TYPE_INTERNATION_LESSONS:
                intent = new Intent(activity, HtmlActivity.class);
                bundle.putInt(KeyMaps.HTML_TYPE, KeyMaps.HtmlType.HTML_TYPE_INTERNATION_LESSON_DETAIL);
                break;

            //默认跳转到购买培优课
            default:
                intent = new Intent(activity, HtmlActivity.class);
                bundle.putInt(KeyMaps.HTML_TYPE, KeyMaps.HtmlType.HTML_TYPE_INTERNATION_LESSON_DETAIL);
                break;
        }

        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    class HtmlApp {
        public HtmlApp() {
        }

        @JavascriptInterface
        public void buyMember() {
            jsHandler.sendEmptyMessage(HANDLER_BUY_MEMBER);
        }

        @JavascriptInterface
        public void clickButtonPrepareCourse() {
            jsHandler.sendEmptyMessage(BUY_COMBOS);
        }

        @JavascriptInterface
        public void recommendShare() {
            jsHandler.sendEmptyMessage(SHARE_WITH_FRIENDS);
        }

        @JavascriptInterface
        public void kindergartenBuy() {
            jsHandler.sendEmptyMessage(KINDERGARTEN_BUY);
        }


        @JavascriptInterface
        public void buyInternationalClass() {
            jsHandler.sendEmptyMessage(BUY_INTERNATIONAL_CLASS);
        }

        @JavascriptInterface
        public void buyMicroLesson() {
            jsHandler.sendEmptyMessage(BUY_MICRO_LESSON);
        }

        @JavascriptInterface
        public void buyNewMember() {
            jsHandler.sendEmptyMessage(BUY_NEW_MEMBER);
        }


        //网页微课和国际版的购买
        @JavascriptInterface
        public void buyMenu(String comboCode, int durationId, String microComboCode) {
            runOnUiThread(() -> {
                LogU.d("做一些请求");
            });
        }

        @JavascriptInterface
        public void callPhone(String calledPhoneNum) {
            runOnUiThread(() -> {
                if (!checkActivityState()) return;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + calledPhoneNum));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            });
        }


        @JavascriptInterface
        public void setTitle(String title) {
            runOnUiThread(() -> {
                tvHeaderTitle.setText(title);
            });
        }

    }

    static class MyHandler extends Handler {
        WeakReference<HtmlActivity> mActivity;

        MyHandler(HtmlActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HtmlActivity tActivity = mActivity.get();
            switch (msg.what) {
                case HANDLER_BUY_MEMBER:

                    break;
                case BUY_COMBOS:
                    break;
                case SHARE_WITH_FRIENDS:

                    break;
                case KINDERGARTEN_BUY:
                    break;
                case BUY_INTERNATIONAL_CLASS:
                    break;
                case BUY_MICRO_LESSON:
                    break;
                case BUY_NEW_MEMBER:

                    break;
            }
        }
    }

    class ClassWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            isOnPageFinished = true;
            if (!checkActivityState()) return;

            if (htmlType != KeyMaps.HtmlType.HTML_TYPE_ADDRESS_LIST) {
                String webTitle = webView == null ? "" : webView.getTitle();
                if (StringU.isNotEmpty(webTitle)) {
                    tvHeaderTitle.setText(webTitle);
                }
            }
            super.onPageFinished(view, url);
        }
    }

    class WebClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (progressBar.getVisibility() == View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.loadUrl("javascript:pause()");
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        jsHandler.removeCallbacksAndMessages(null);
//        webView.stopLoading();
        frameLayout.removeView(webView);
        webView.removeAllViews();
        webView.destroy();
        webView = null;
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {

        if (htmlType == KeyMaps.HtmlType.HTML_TYPE_ADDRESS_LIST) {
            super.onBackPressed();
            return;
        }

        if (webView != null && isOnPageFinished && webView.canGoBack()) {
            webView.goBack();
        } else {
            if (htmlType == KeyMaps.HtmlType.HTML_TYPE_PUBLIC_CLASS) {
                LogU.d("做操作");
            }
            super.onBackPressed();
        }

    }

}
