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
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.databinding.AtyDataBindingListBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingListActivity extends RxAppCompatActivity {

    AtyDataBindingListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding_list);
        binding.setPresenter(new Presenter());

        binding.rvView.setLayoutManager(new LinearLayoutManager(this));
    }


    public class Presenter {

        public void onClickAddItem(View view) {

        }

        public void onClickRemoveItem(View view) {
        }
    }

}
