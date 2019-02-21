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
import com.mao.cn.learnDevelopProject.databinding.AtyDataBindingExpressionBinding;
import com.mao.cn.learnDevelopProject.model.Employee;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingExpressionActivity extends RxAppCompatActivity {

    AtyDataBindingExpressionBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding_expression);
        binding.setPresenter(new Presenter());
        Employee employee = new Employee("zhang","ke");
        employee.setmAvatar("https://img1.mukewang.com/user/545868ff0001bfbb02200220-40-40.jpg");
        binding.setEmployee(employee);

    }

    public class Presenter {

    }

}
