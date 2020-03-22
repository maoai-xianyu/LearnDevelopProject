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
import androidx.databinding.OnRebindCallback;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.databinding.AtyDataBindingAnimationBinding;
import com.mao.cn.learnDevelopProject.model.Employee;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingAnimationActivity extends RxAppCompatActivity {

    AtyDataBindingAnimationBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding_animation);
        Employee employee = new Employee("zhang", "ke");
        employee.setmAvatar("https://img1.mukewang.com/user/545868ff0001bfbb02200220-40-40.jpg");
        binding.setEmployee(employee);

        binding.setPresenter(new Presenter());

        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                ViewGroup root = (ViewGroup) binding.getRoot();
                TransitionManager.beginDelayedTransition(root);
                return true;
            }
        });

    }

    public class Presenter {

        public void onCheckedChanged(View buttonView, boolean isChecked) {
            binding.setShowImage(isChecked);
        }
    }


}
