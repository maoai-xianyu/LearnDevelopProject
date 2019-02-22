// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.databinding.AtyDataBindingLambdaBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingLambdaActivity extends RxAppCompatActivity {

    AtyDataBindingLambdaBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding_lambda);

    }

    public class Presenter {

    }

}
