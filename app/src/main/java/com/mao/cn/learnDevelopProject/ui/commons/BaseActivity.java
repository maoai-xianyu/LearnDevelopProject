package com.mao.cn.learnDevelopProject.ui.commons;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Toast;

import com.hwangjr.rxbus.RxBus;
import com.mao.cn.learnDevelopProject.LearnDevelopApplication;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.common.CommActivity;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.converter.RetrofitError;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.utils.tools.JsonU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;
import com.mao.cn.learnDevelopProject.wedget.animation.animationeffects.Effectstype;
import com.mao.cn.learnDevelopProject.wedget.dialog.DefineTwoBottomDialog;
import com.mao.cn.learnDevelopProject.wedget.dialog.LoadingDialog;
import com.mao.cn.learnDevelopProject.wedget.dialog.SingleDialog;

import butterknife.ButterKnife;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangkun on 2017/6/9.
 */

public abstract class BaseActivity extends CommActivity implements BaseViewInferface, ViewTreeObserver.OnGlobalLayoutListener {


    protected DefineTwoBottomDialog twoBottomDialog;
    protected SingleDialog singleDialog;
    protected LoadingDialog loadingDialog;


    public void init() {
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        RxBus.get().register(this);
        super.init();
    }

    @Override
    public void setting() {
        LogU.i(" activity " + getClass().getName());
        LearnDevelopApplication.addAty(activity);
        setupComponent(LearnDevelopApplication.getComponent());

    }

    public void setScreenBackground(float alphaParams) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = alphaParams;
        this.getWindow().setAttributes(params);
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (!checkActivityState()) return;
        activity.runOnUiThread(() -> {
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(activity);
            }
            loadingDialog.show(msg);
        });

    }

    @Override
    public void showLoadingDialog(int msg) {
        if (!checkActivityState()) return;
        showLoadingDialog(getString(msg));
    }

    @Override
    public void hideLoadingDialog() {
        if (!checkActivityState()) return;
        runOnUiThread(() -> {
            if (loadingDialog != null && loadingDialog.isShowing())
                loadingDialog.dismiss();
        });

    }

    @Override
    public void onTip(String msg) {
        if (!checkActivityState()) return;
        runOnUiThread(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
    }


    @Override
    public void onTip(int msg) {
        if (!checkActivityState()) return;
        onTip(getString(msg));
    }


    @Override
    public void interError(RetrofitError error) {
        int status = error.getCode();
        if (status == RetrofitError.ERROR_CONNECTION) {
            return;
        }
        switch (status) {
            case ValueMaps.ResponeCode.TYPE_CODE_401:
                String content = JsonU.getString(error.getBody(), "error");
                if (StringU.isEmpty(content))
                    accessError(error.getRequestUrl());
                else
                    accessError(content, error.getRequestUrl());
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_404:
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_500:
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_501:
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_502:
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_503:
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_304:
                break;
            case ValueMaps.ResponeCode.TYPE_CODE_403:
                break;
        }
    }

    @Override
    public void interError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            int code = httpException.code();
            if (code == ValueMaps.ResponeCode.TYPE_CODE_401) {
                accessError("");
            }
        }
    }

    public void accessError(String errorMessage) {
        accessError(getString(R.string.tip_token_error), errorMessage);
    }

    protected void accessError(String message, String errorMessage) {
        if (singleDialog == null) {
            singleDialog = new SingleDialog(activity);
        }
        if (!singleDialog.isShowing()) {
            Effectstype effect = Effectstype.Slidetop;
            singleDialog.seTouchViewtCancle(false)
                    .withMessage(message)
                    .isCancelableOnTouchOutside(false) // def | isCancelable(true)
                    .withDuration(700) // def
                    .withEffect(effect) // def Effectstype.Slidetop
                    .withTitle(getString(R.string.tips))
                    .setButtonClick(new TokenErrorListener());
            if (!activity.isFinishing())
                singleDialog.show();
        }
    }

    @Override
    public void onGlobalLayout() {

    }

    class TokenErrorListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            singleDialog.dismiss();

            // TODO: 2017/8/3  退出登录
        }
    }


    protected abstract void setupComponent(AppComponent appComponent);

    public boolean checkActivityState() {
        return activity != null && !activity.isFinishing();
    }

    @Override
    protected void onDestroy() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        if (twoBottomDialog != null) {
            twoBottomDialog.dismiss();
            twoBottomDialog.setButtonLeftClick(null).setButtonRightClick(null);
            twoBottomDialog = null;
        }
        if (singleDialog != null) {
            singleDialog.dismiss();
            singleDialog.setButtonClick(null);
            singleDialog = null;
        }
        LearnDevelopApplication.removeAty(activity);
        super.onDestroy();
    }

    protected <T> Observable.Transformer<T, T> timer() {
        return observable -> observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
