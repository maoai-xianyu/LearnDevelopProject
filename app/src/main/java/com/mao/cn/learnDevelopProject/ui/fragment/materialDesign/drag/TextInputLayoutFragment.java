package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.drag;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import java.util.Objects;

import butterknife.BindView;

public class TextInputLayoutFragment extends BaseFragment {

    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;


    public static TextInputLayoutFragment newInstance() {
        TextInputLayoutFragment fragment = new TextInputLayoutFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_md_textinputlayput;
    }

    @Override
    public void initView() {

        // 开启计数
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(10); // 最大输入个数


    }

    @Override
    public void setListener() {


        Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new MinLengthTestWatcher("长度不要超过6个"));


    }

    class MinLengthTestWatcher implements TextWatcher {
        private String errorTips;

        public MinLengthTestWatcher(String errorTips) {
            this.errorTips = errorTips;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // 文字变化前的回调

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 文字变化

        }

        @Override
        public void afterTextChanged(Editable s) {
            // 文字变化后的回调
            if (textInputLayout.getEditText().getText().toString().length() <= 6){
                textInputLayout.setErrorEnabled(false);

            }else {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errorTips);

            }

        }
    }


    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
