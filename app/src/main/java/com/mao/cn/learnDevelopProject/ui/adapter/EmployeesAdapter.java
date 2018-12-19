package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mao.cn.learnDevelopProject.BR;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.model.Employees;
import com.mao.cn.learnDevelopProject.ui.viewholder.BindingViewHolder;
import com.mao.cn.learnDevelopProject.utils.tools.RandomU;

import java.util.ArrayList;
import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2018/12/4.
 */
public class EmployeesAdapter extends RecyclerView.Adapter<BindingViewHolder> {


    private static final int ITEM_VIEW_TYPE_ON = 1;
    private static final int ITEM_VIEW_TYPE_OFF = 2;

    private final LayoutInflater mLayoutInflater;


    private OnItemClickListener mListener;

    private List<Employees> mEmployeesList;


    public EmployeesAdapter(Context context) {
        // 获取LayoutInflater
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEmployeesList = new ArrayList<>();
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding;
        if (i == ITEM_VIEW_TYPE_ON) {
            binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_employees_fire_on, viewGroup, false);
        } else {
            binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_employees_fire_off, viewGroup, false);

        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder bindingViewHolder, int i) {
        final Employees employees = mEmployeesList.get(i);
        bindingViewHolder.getBinding().setVariable(BR.item, employees);
        bindingViewHolder.getBinding().executePendingBindings();
        bindingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onEmployeesClick(employees);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEmployeesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        final Employees employees = mEmployeesList.get(position);
        if (employees.isFired.get()) {
            return ITEM_VIEW_TYPE_OFF;
        } else {
            return ITEM_VIEW_TYPE_ON;
        }
    }

    public interface OnItemClickListener {
        void onEmployeesClick(Employees employees);
    }


    public void addAll(List<Employees> list) {
        mEmployeesList.clear();
        mEmployeesList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(Employees employees) {
        int random = RandomU.getRandom(mEmployeesList.size() + 1);
        mEmployeesList.add(random, employees);
        notifyItemInserted(random);
    }

    public void remove() {
        if (mEmployeesList.size() == 0) {
            return;
        }
        int random = RandomU.getRandom(mEmployeesList.size());

        mEmployeesList.remove(random);
        notifyItemRemoved(random);
    }
}
