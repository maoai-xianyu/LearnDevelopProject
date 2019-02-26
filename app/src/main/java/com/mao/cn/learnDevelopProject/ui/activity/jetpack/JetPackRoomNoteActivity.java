package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import java.util.UUID;

public class JetPackRoomNoteActivity extends AppCompatActivity {


    private String TAG = this.getClass().getSimpleName();
    private JetPackRoomNoteViewModel roomViewModel;
    private int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;

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

        roomViewModel = ViewModelProviders.of(this).get(JetPackRoomNoteViewModel.class);
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
        } else {
            ToastUtils.show("不能保存");
        }
    }
}