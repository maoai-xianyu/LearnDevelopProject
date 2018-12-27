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
import com.mao.cn.learnDevelopProject.model.Employees;
import com.mao.cn.learnDevelopProject.ui.adapter.EmployeesAdapter;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DataBindingListActivity extends RxAppCompatActivity {

    AtyDataBindingListBinding binding;

    EmployeesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_data_binding_list);
        binding.setPresenter(new Presenter());

        binding.includeHeader.ibHeaderBack.setVisibility(View.VISIBLE);
        binding.includeHeader.ibHeaderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.includeHeader.tvHeaderTitle.setText("DataBindingList");


        binding.rvView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeesAdapter(this);
        binding.rvView.setAdapter(adapter);
        adapter.setmListener(new EmployeesAdapter.OnItemClickListener() {
            @Override
            public void onEmployeesClick(Employees employees) {
                ToastUtils.show(employees.getFirstName());
            }
        });
        List<Employees> employeesList = new ArrayList<>();
        employeesList.add(new Employees("zk", "kun", false));
        employeesList.add(new Employees("zk1", "kun1", false));
        employeesList.add(new Employees("zk2", "kun2", true));
        employeesList.add(new Employees("zk3", "kun3", false));
        adapter.addAll(employeesList);


    }


    public class Presenter {

        public void onClickAddItem(View view) {

            adapter.add(new Employees("zk4", "kun4", false));


        }

        public void onClickRemoveItem(View view) {
            adapter.remove();
        }
    }

}
