package com.mao.cn.learnDevelopProject.ui.viewholder;

import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author:  zhangkun .
 * date:    on 2018/12/4.
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public BindingViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
