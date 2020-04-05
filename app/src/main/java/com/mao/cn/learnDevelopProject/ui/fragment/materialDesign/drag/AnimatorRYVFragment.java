package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.drag;

import android.os.Bundle;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class AnimatorRYVFragment extends BaseFragment implements QQAdapter.StartDragListener {


    @BindView(R.id.rv)
    RecyclerView rv;

    private ItemTouchHelper itemTouchHelper;

    public static AnimatorRYVFragment newInstance() {
        AnimatorRYVFragment fragment = new AnimatorRYVFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_rv_animator;
    }

    @Override
    public void initView() {

        List<QQMessage> list = DataUtils.init();
        QQAdapter adapter = new QQAdapter(list, this);
        rv.setAdapter(adapter);
        //条目触摸帮助类
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
