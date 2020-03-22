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

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.databinding.AtyDataBindingExpressionBinding;
import com.mao.cn.learnDevelopProject.model.Employee;
import com.mao.cn.learnDevelopProject.model.FormModel;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Random;


/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingExpressionActivity extends RxAppCompatActivity {

    AtyDataBindingExpressionBinding binding;

    Random random = new Random(System.currentTimeMillis());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding_expression);
        Employee employee = new Employee("zhang", "ke");
        employee.setFired(random.nextBoolean());
        employee.setmAvatar("https://img1.mukewang.com/user/545868ff0001bfbb02200220-40-40.jpg");
        binding.setEmployee(employee);


        FormModel formModel = new FormModel("zhang", "nan");
        binding.setModel(formModel);
        formModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

                //propertyId  BR对应的值
                ToastUtils.show(String.valueOf(propertyId));

            }
        });


    }


}
