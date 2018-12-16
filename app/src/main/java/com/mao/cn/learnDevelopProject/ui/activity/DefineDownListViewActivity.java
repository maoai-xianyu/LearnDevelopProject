// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 09/28/2017 11:40 上午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.DensityUtil;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class DefineDownListViewActivity extends BaseActivity {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;

    @BindView(R.id.etInput)
    EditText etInput;
    @BindView(R.id.ivDown)
    ImageView ivDown;

    private PopupWindow popupWindow;
    private RecyclerView recyclerView;
    private List<String> msgs;
    private DataAdapter dataAdapter;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_down_list_view;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("下拉框");
        tvHeaderTitle.setVisibility(View.VISIBLE);

        msgs = new ArrayList<>();
        recyclerView = new RecyclerView(this);
        recyclerView.setBackgroundResource(R.color.c_FFFFFF);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 500; i++) {
            msgs.add(i + "aaaaaaaaaaa" + i);
        }
        dataAdapter = new DataAdapter();
        recyclerView.setAdapter(dataAdapter);
    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(etInput).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            if (popupWindow == null) {
                popupWindow = new PopupWindow(DefineDownListViewActivity.this);
                popupWindow.setWidth(etInput.getWidth());
                popupWindow.setHeight(DensityUtil.dip2px(DefineDownListViewActivity.this, 200));
                popupWindow.setContentView(recyclerView);
                popupWindow.setFocusable(true);//设置焦点
            }
            popupWindow.showAsDropDown(etInput, 0, 0);

        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        dataAdapter.setClickListener(new ClickListener() {
            @Override
            public void deleteItem(int position) {
                dataAdapter.deleteItme(position);
            }

            @Override
            public void clickItem(String msg) {
                etInput.setText(msg);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
    }

    class DataAdapter extends RecyclerView.Adapter<DataAdapter.DadaViewHold> {

        private ClickListener clickListener;

        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @NonNull
        @Override
        public DadaViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_data_popup, viewGroup, false);
            return new DadaViewHold(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DadaViewHold dadaViewHold, int i) {

            dadaViewHold.tvPhone.setText(msgs.get(i));

            RxView.clicks(dadaViewHold.ivDelete).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                    .MILLISECONDS).subscribe(aVoid -> {
                clickListener.deleteItem(i);
            }, throwable -> {
                LogU.e(throwable.getMessage());
            });
            RxView.clicks(dadaViewHold.view).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                    .MILLISECONDS).subscribe(aVoid -> {
                clickListener.clickItem(msgs.get(i));
            }, throwable -> {
                LogU.e(throwable.getMessage());
            });
        }

        @Override
        public int getItemCount() {
            return ListU.notEmpty(msgs) ? msgs.size() : 0;
        }


        void deleteItme(int i) {
            notifyItemRemoved(i);
        }


        class DadaViewHold extends RecyclerView.ViewHolder {

            View view;
            @BindView(R.id.ivDelete)
            ImageView ivDelete;
            @BindView(R.id.tvPhone)
            TextView tvPhone;

            public DadaViewHold(@NonNull View itemView) {
                super(itemView);
                this.view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }

    }

    interface ClickListener {
        void deleteItem(int position);

        void clickItem(String msg);
    }


    @Override
    protected void onDestroy() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        super.onDestroy();
    }
}
