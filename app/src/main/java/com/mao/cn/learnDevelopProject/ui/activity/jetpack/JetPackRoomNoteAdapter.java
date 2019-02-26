package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;

import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2019/2/26.
 */
public class JetPackRoomNoteAdapter extends RecyclerView.Adapter<JetPackRoomNoteAdapter.ViewHolder> {


    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<JetPackRoomNote> mNotes;

    public JetPackRoomNoteAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_jet_pach_list_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (mNotes != null) {
            JetPackRoomNote roomNote = mNotes.get(i);
            viewHolder.setData(roomNote.getNote(), i);
        } else {
            viewHolder.noteItemView.setText("没有笔记");
        }

    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public void setmNotes(List<JetPackRoomNote> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteItemView;
        private int mPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.tvNote);
        }

        public void setData(String note, int position) {
            noteItemView.setText(note);
            mPosition = position;
        }
    }
}
