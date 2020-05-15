package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.model.Fruit;
import com.mao.cn.learnDevelopProject.ui.adapter.define.FruitAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.wedget.materialDesign.DividerGridItemDecoration;
import com.mao.cn.learnDevelopProject.wedget.materialDesign.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class RecyclerViewDividerFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;


    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple), new Fruit("Banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange), new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear), new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple), new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry), new Fruit("Mango", R.drawable.mango)};

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;


    public static RecyclerViewDividerFragment newInstance() {
        RecyclerViewDividerFragment fragment = new RecyclerViewDividerFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_recyclerview_divider;
    }

    @Override
    public void initView() {
        initFruits();

        adapter = new FruitAdapter(fruitList);
        //rv.addItemDecoration(new RecyclerViewItemDivider());
        //rv.addItemDecoration(new RecyclerViewItemDrawableDivider(context, R.drawable.divider_bg02));
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rv.setLayoutManager(layoutManager);*/
        rv.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new RecyclerViewItemGridFixDrawableDivider(context, R.drawable.divider_bg02));
    }


    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public void setListener() {


        RxView.clicks(tv1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            rv.setLayoutManager(layoutManager);
            rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tv2).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {

            GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
            rv.setLayoutManager(layoutManager);
            rv.addItemDecoration(new DividerGridItemDecoration(context));
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }


    class RecyclerViewItemDivider extends RecyclerView.ItemDecoration {

        private Paint mPaint;

        public RecyclerViewItemDivider() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.RED);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            // 代表在每个底部的位置留出10px来绘制分割线 最后一个位置不需要分割线
            int position = parent.getChildAdapterPosition(view);

            LogU.e("position --> " + position + "  " + parent.getChildCount());
            // parent.getChildCount() 是不断变化的 现在没办法保证最后一条
            // 保证第一条
            // if(position != parent.getChildCount()-1){
            if (position != 0) {
                outRect.top = 10;
            }
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);

            int childCount = parent.getChildCount();

            Rect rect = new Rect();

            rect.left = parent.getPaddingLeft();
            rect.right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 1; i < childCount; i++) {
                rect.bottom = parent.getChildAt(i).getTop();
                rect.top = rect.bottom - 10;
                c.drawRect(rect, mPaint);
            }

        }
    }


    class RecyclerViewItemDrawableDivider extends RecyclerView.ItemDecoration {

        private Drawable mDivider;


        public RecyclerViewItemDrawableDivider(Context context, int drawableResId) {
            mDivider = ContextCompat.getDrawable(context, drawableResId);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            // 代表在每个底部的位置留出10px来绘制分割线 最后一个位置不需要分割线
            int position = parent.getChildAdapterPosition(view);

            LogU.e("position --> " + position + "  " + parent.getChildCount());

            if (position != 0) {
                outRect.top = mDivider.getIntrinsicHeight();
            }
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);

            int childCount = parent.getChildCount();

            Rect rect = new Rect();

            rect.left = parent.getPaddingLeft();
            rect.right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 1; i < childCount; i++) {
                rect.bottom = parent.getChildAt(i).getTop();
                rect.top = rect.bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(rect);
                mDivider.draw(c);
            }

        }
    }


    class RecyclerViewItemGridDrawableDivider extends RecyclerView.ItemDecoration {

        private Drawable mDivider;


        public RecyclerViewItemGridDrawableDivider(Context context, int drawableResId) {
            mDivider = ContextCompat.getDrawable(context, drawableResId);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = mDivider.getIntrinsicHeight();
            outRect.right = mDivider.getIntrinsicWidth();
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            drawVertical(c, parent);
            drawHorizontal(c, parent);
        }

        private void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                int left = view.getLeft() - layoutParams.leftMargin;
                int right = view.getRight() + mDivider.getIntrinsicWidth() + layoutParams.rightMargin;
                int top = view.getBottom() + layoutParams.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        private void drawVertical(Canvas c, RecyclerView parent) {

            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                int top = view.getTop() - layoutParams.topMargin;
                int bottom = view.getBottom() + layoutParams.bottomMargin;
                int left = view.getRight() + layoutParams.rightMargin;
                int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

            }
        }
    }


    class RecyclerViewItemGridFixDrawableDivider extends RecyclerView.ItemDecoration {

        private Drawable mDivider;


        public RecyclerViewItemGridFixDrawableDivider(Context context, int drawableResId) {
            mDivider = ContextCompat.getDrawable(context, drawableResId);
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            drawVertical(c, parent);
            drawHorizontal(c, parent);
        }

        private void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                int left = view.getLeft() - layoutParams.leftMargin;
                int right = view.getRight() + mDivider.getIntrinsicWidth() + layoutParams.rightMargin;
                int top = view.getBottom() + layoutParams.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        private void drawVertical(Canvas c, RecyclerView parent) {

            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                int top = view.getTop() - layoutParams.topMargin;
                int bottom = view.getBottom() + layoutParams.bottomMargin;
                int left = view.getRight() + layoutParams.rightMargin;
                int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);

            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

            int bottom = mDivider.getIntrinsicHeight();
            int right = mDivider.getIntrinsicWidth();

            if (isLastColumn(parent, currentPosition)) { // 最后一列
                right = 0;
            }

            if (isLastRow(parent, currentPosition)) { // 最后一行
                bottom = 0;
            }
            outRect.bottom = bottom;
            outRect.right = right;


        }


        // 是否是最后一列
        public boolean isLastColumn(RecyclerView parent, int currentPosition) {

            int spanCount = getSpanCount(parent);

            return (currentPosition + 1) % spanCount == 0;
        }

        public boolean isLastRow(RecyclerView parent, int currentPosition) {

            int childCount = Objects.requireNonNull(parent.getAdapter()).getItemCount();
            int spanCount = getSpanCount(parent);

            // 行数
            int rowNum = childCount / spanCount == 0 ? childCount / spanCount : childCount / spanCount + 1;

            return currentPosition > (rowNum - 1) * spanCount - 1;
        }


        // 获取列数
        public int getSpanCount(RecyclerView parent) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                return ((GridLayoutManager) layoutManager).getSpanCount();
            }
            return 1;
        }


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
