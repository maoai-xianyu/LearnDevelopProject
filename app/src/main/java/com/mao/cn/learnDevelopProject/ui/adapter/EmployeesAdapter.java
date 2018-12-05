package com.mao.cn.learnDevelopProject.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mao.cn.learnDevelopProject.model.Employees;
import com.mao.cn.learnDevelopProject.ui.viewholder.BindingViewHolder;

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
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEmployeesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder bindingViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public interface OnItemClickListener {
        void onEmployeesClick(Employees employees);
    }
}
