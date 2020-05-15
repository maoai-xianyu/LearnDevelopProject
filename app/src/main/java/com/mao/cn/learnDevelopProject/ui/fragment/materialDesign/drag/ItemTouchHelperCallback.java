package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.drag;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.cn.learnDevelopProject.R;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener moveListener;

    public ItemTouchHelperCallback(ItemTouchMoveListener moveListener) {
        this.moveListener = moveListener;
    }

    // Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向（意思就是我要监听哪个方向的拖动）
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder holder) {
        //方向：up,down,left,right
        //常量：
        int up = ItemTouchHelper.UP;//1  0x0001
        int down = ItemTouchHelper.DOWN;//2 0x0010
        // ItemTouchHelper.LEFT
        // ItemTouchHelper.RIGHT
        // 我要监听的拖拽方向是哪两个方向。
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // 或运算  & 与运算
        // 我要监听的swipe侧滑方向是哪个方向
        // int swipeFlags = 0; // 不监听方向 侧滑
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(dragFlags, swipeFlags);
        return flags;
    }

    // 当移动的时候回调的方法--拖拽
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {

        // 不同的布局文件不能交换，具体根据产品来
        if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) {
            return false;
        }
        // 在拖拽的过程当中不断地调用 adapter.notifyItemMoved(from,to);
        boolean result = moveListener.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
        return result;
    }

    // 侧滑的时候回调的
    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int arg1) {
        // 监听侧滑，1.删除数据；2.调用adapter.notifyItemRemove(position)
        moveListener.onItemRemove(holder.getLayoutPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        // 是否允许长按拖拽效果
        return true;
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // 判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.c_ff4D30));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 恢复
        viewHolder.itemView.setBackgroundColor(Color.WHITE);

        // 方案一 复用问题恢复
       /* viewHolder.itemView.setAlpha(1);//1~0
        viewHolder.itemView.setScaleX(1);//1~0
        viewHolder.itemView.setScaleY(1);//1~0*/
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {
        // dX:水平方向移动的增量（负：往左；正：往右）范围：0~View.getWidth  0~1
        //透明度动画
        float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            viewHolder.itemView.setAlpha(alpha);//1~0
            viewHolder.itemView.setScaleX(alpha);//1~0
            viewHolder.itemView.setScaleY(alpha);//1~0
        }
        // 方案二
        /*if (alpha == 0) {
            viewHolder.itemView.setAlpha(1);//1~0
            viewHolder.itemView.setScaleX(1);//1~0
            viewHolder.itemView.setScaleY(1);//1~0
        }*/


        // qq 效果 滑动到一半， 判断是否超出或者达到 width/2 就让其 setTranslationX 设置一半的位置  有问题
        /*if (Math.abs(dX) <= viewHolder.itemView.getWidth() / 2) {
            viewHolder.itemView.setTranslationX(-0.5f * viewHolder.itemView.getWidth());
        } else {
            viewHolder.itemView.setTranslationX(dX);
        }*/


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                isCurrentlyActive);
    }


    public interface ItemTouchMoveListener {

        /**
         * 当拖拽的时候回调
         * 可以在此方法里面实现：拖拽条目并实现刷新效果
         *
         * @param fromPosition 从什么位置拖
         * @param toPosition   到什么位置
         * @return 是否执行了move  可以不用返回值 在 onMove 直接返回 true
         */
        boolean onItemMove(int fromPosition, int toPosition);

        /**
         * 当条目被移除是回调
         *
         * @param position 移除的位置
         * @return
         */
        boolean onItemRemove(int position);
    }


}