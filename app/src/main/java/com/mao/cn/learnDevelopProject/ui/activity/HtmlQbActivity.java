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
import android.view.View;
import android.webkit.JavascriptInterface;
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
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;


public class HtmlQbActivity extends BaseActivity {

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

    @BindView(R.id.progress_bar)
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


    //X5WebView webView;
    MyHandler jsHandler;
    boolean isOnPageFinished;

    @Override
    public void getArgs(Bundle bundle) {
        if (bundle != null) {
            htmlType = bundle.getInt(KeyMaps.HTML_TYPE);
            htmlUrl = bundle.getString(KeyMaps.HTML_URL);
        }

    }

    @Override
    public int setView() {
        return R.layout.aty_qb_html;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        setResult(RESULT_OK);
        jsHandler = new MyHandler(this);


         /* webView = new X5WebView(MainActivity.this, null);
        frameLayout.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));*/

        WebSettings webSetting = webView.getSettings();

        webView.setWebViewClient(new ClassWebView());
        webView.setWebChromeClient(new WebClient());
        webView.addJavascriptInterface(new HtmlApp(), "androidMujiGame");
        // boxfish
        //webView.addJavascriptInterface(new HtmlApp(), "android");

        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.requestFocus();

        if (StringU.isNotEmpty(htmlUrl)) {
            webView.loadUrl(htmlUrl);
        } else {
            switch (htmlType) {
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

            htmlUrl = "https://developer.mujigame.com";

            //webView.loadUrl("http://debugtbs.qq.com");
//        webView.loadUrl("http://soft.imtt.qq.com/browser/tes/feedback.html");

        /*webView.postDelayed(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(htmlUrl);
            }
        }, 500);*/

            webView.loadUrl(htmlUrl);
        }

        LogU.d("------htmlurl=" + htmlUrl);


    }

    private void setRightTitle(String rightText) {
        tvHeaderRight.setText(rightText);
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setTextColor(getResources().getColor(R.color.c_f5b100));
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
                case KeyMaps.HtmlType.HTML_TYPE_REVOLUTION:
                    startToBuyActivity(activity, KeyMaps.InternationalMember.HTML_TYPE_FOREIGN_TEACHER_LESSONS);
                    break;
                case KeyMaps.HtmlType.HTML_TYPE_FOREIGN_TEACHER_LESSON_DETAIL:
                    startToBuyActivity(activity, KeyMaps.InternationalMember.HTML_TYPE_FOREIGN_TEACHER_LESSONS);
                    break;

            }

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    public static void startToBuyActivity(Activity activity, String memberState) {
        Intent intent = null;
        Bundle bundle = new Bundle();
        intent = new Intent(activity, HtmlQbActivity.class);
        bundle.putInt(KeyMaps.HTML_TYPE, KeyMaps.HtmlType.HTML_TYPE_INTERNATION_LESSON_DETAIL);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    class HtmlApp {
        public HtmlApp() {
        }


        @JavascriptInterface
        public void skip(String url) {
            LogU.d("name " + url);
            runOnUiThread(() -> {
                Bundle bundle = new Bundle();
                bundle.putInt(KeyMaps.HTML_TYPE, 0);
                bundle.putString(KeyMaps.HTML_URL, url);
                startActivity(HtmlQbActivity.class, bundle);
            });
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
        WeakReference<HtmlQbActivity> mActivity;

        MyHandler(HtmlQbActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HtmlQbActivity tActivity = mActivity.get();
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
