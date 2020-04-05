package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.drag;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mao.cn.learnDevelopProject.R;

import java.util.Collections;
import java.util.List;

public class QQAdapter extends RecyclerView.Adapter<QQAdapter.MyViewHolder> implements ItemTouchHelperCallback.ItemTouchMoveListener {
    private List<QQMessage> list;
    private StartDragListener dragListener;

    public QQAdapter(List<QQMessage> list, StartDragListener dragListener) {
        this.list = list;
        this.dragListener = dragListener;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        QQMessage qqMessage = list.get(position);
        holder.iv_logo.setImageResource(qqMessage.getLogo());
        holder.tv_name.setText(qqMessage.getName());
        holder.tv_Msg.setText(qqMessage.getLastMsg());
        holder.tv_time.setText(qqMessage.getTime());

        holder.iv_logo.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //传递触摸情况给谁？
                dragListener.onStartDrag(holder);
            }
            return false;
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // 1.数据交换；2.刷新
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        // notifyItemChanged(int position)，position数据发生了改变，那调用这个方法，就会回调对应position的onBindViewHolder()方法了，
        // 当然，因为ViewHolder是复用的，所以如果position在当前屏幕以外，也就不会回调了，因为没有意义，下次position滚动会当前屏幕以内的时候同样会调用onBindViewHolder()方法刷新数据了
        notifyItemRangeChanged(position, list.size() - position);
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_logo;
        private TextView tv_name;
        private TextView tv_Msg;
        private TextView tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_Msg = (TextView) itemView.findViewById(R.id.tv_lastMsg);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }

    }


    public interface StartDragListener {
        /**
         * 该接口用于需要主动回调拖拽效果的
         *
         * @param viewHolder
         */
        public void onStartDrag(RecyclerView.ViewHolder viewHolder);

    }


}
