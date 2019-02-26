package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
            viewHolder.setListeners();
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteItemView;
        private ImageView ivRowDelete;
        private ImageView ivRowEdit;
        private int mPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.tvNote);
            ivRowDelete = itemView.findViewById(R.id.ivRowDelete);
            ivRowEdit = itemView.findViewById(R.id.ivRowEdit);
        }

        public void setData(String note, int position) {
            noteItemView.setText(note);
            mPosition = position;
        }

        public void setListeners() {
            ivRowEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, JetPackRoomNoteEditActivity.class);
                    intent.putExtra("note_id", mNotes.get(mPosition).getId());
                    ((Activity)mContext).startActivityForResult(intent,JetPackRoomNoteActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
                }
            });
            ivRowDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
