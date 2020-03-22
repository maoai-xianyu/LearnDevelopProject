package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import java.util.List;
import java.util.UUID;

public class JetPackRoomNoteActivity extends AppCompatActivity {


    private String TAG = this.getClass().getSimpleName();
    private JetPackRoomNoteViewModel roomViewModel;
    private final static int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    public final static int UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2;
    private JetPackRoomNoteAdapter jetPackRoomNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jet_pack_room);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JetPackRoomNoteActivity.this, JetPackRoomNewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rvView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jetPackRoomNoteAdapter = new JetPackRoomNoteAdapter(this);
        recyclerView.setAdapter(jetPackRoomNoteAdapter);

        roomViewModel = ViewModelProviders.of(this).get(JetPackRoomNoteViewModel.class);

        roomViewModel.getAllNotes().observe(this, new Observer<List<JetPackRoomNote>>() {
            @Override
            public void onChanged(@Nullable List<JetPackRoomNote> jetPackRoomNotes) {
                jetPackRoomNoteAdapter.setmNotes(jetPackRoomNotes);
            }
        });

        jetPackRoomNoteAdapter.setListener(new JetPackRoomNoteAdapter.OnDeleteClickListener() {
            @Override
            public void OnDeleteClickListener(JetPackRoomNote roomNote) {
                // code delete item
                roomViewModel.delete(roomNote);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // code to insert note
            final String note_id = UUID.randomUUID().toString();
            JetPackRoomNote roomNote = new JetPackRoomNote(note_id, data.getStringExtra(JetPackRoomNewNoteActivity.NOTE_ADDED));
            roomViewModel.insert(roomNote);
            ToastUtils.show("保存");
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // code to update note
            String noteID = data.getStringExtra(JetPackRoomNoteEditActivity.NOTE_ID);
            String updateText = data.getStringExtra(JetPackRoomNoteEditActivity.UPDATE_NOTE);
            JetPackRoomNote roomNote = new JetPackRoomNote(noteID, updateText);
            roomViewModel.update(roomNote);
            ToastUtils.show("更新数据");
        } else {
            ToastUtils.show("不能保存");
        }
    }
}
