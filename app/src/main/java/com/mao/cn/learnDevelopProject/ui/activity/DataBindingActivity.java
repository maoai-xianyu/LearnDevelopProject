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
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.databinding.AtyDataBindingBinding;
import com.mao.cn.learnDevelopProject.model.Employee;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingActivity extends RxAppCompatActivity {

    private Employee mEmployee = new Employee("Tom", "Jack");
    AtyDataBindingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding);
        binding.setEmployee(mEmployee);
        binding.setPresenter(new Presenter());
        //binding.setVariable(BR.employee, mEmployee);
    }


    public class Presenter {

        public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            mEmployee.setFirstName(text.toString());
            binding.setEmployee(mEmployee);
        }

        public void onClick(View view){
            ToastUtils.show("点击了");
        }

        public void onClickListenerBinding(Employee employee){
            ToastUtils.show("点击了"+employee.getLastName());
        }
    }

}
