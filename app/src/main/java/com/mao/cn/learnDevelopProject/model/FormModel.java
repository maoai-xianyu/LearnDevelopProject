package com.mao.cn.learnDevelopProject.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mao.cn.learnDevelopProject.BR;

/**
 * author:  zhangkun .
 * date:    on 2018/11/15.
 */
public class FormModel extends BaseObservable {

    private String formName;
    private String formPassword;


    public FormModel(String formName, String formPassword) {
        this.formName = formName;
        this.formPassword = formPassword;
    }

    @Bindable
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
        notifyPropertyChanged(BR.formName);
    }

    @Bindable
    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
        notifyPropertyChanged(BR.formPassword);
    }
}

