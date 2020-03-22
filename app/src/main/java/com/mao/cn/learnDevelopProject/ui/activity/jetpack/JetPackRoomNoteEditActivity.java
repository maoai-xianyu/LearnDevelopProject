package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mao.cn.learnDevelopProject.R;

public class JetPackRoomNoteEditActivity extends AppCompatActivity {


    public static final String NOTE_ID = "note_id";
    public static final String UPDATE_NOTE = "note_text";
    private String TAG = this.getClass().getSimpleName();
    private EditText etNote;
    private JetPackRoomNoteEditViewModel jetPackRoomNoteEditViewModel;
    private Bundle bundle;
    private String noteId;
    private LiveData<JetPackRoomNote> noteLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jet_pack_room_edit);

        etNote = findViewById(R.id.etNote);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            noteId = bundle.getString("note_id");
        }

        jetPackRoomNoteEditViewModel = ViewModelProviders.of(this).get(JetPackRoomNoteEditViewModel.class);

        // 查询
        noteLiveData = jetPackRoomNoteEditViewModel.getNoteFromId(noteId);

        noteLiveData.observe(this, new Observer<JetPackRoomNote>() {
            @Override
            public void onChanged(@Nullable JetPackRoomNote roomNote) {
                etNote.setText(roomNote.getNote());
            }
        });


    }

    public void updateNote(View view) {

        String update = etNote.getText().toString();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(NOTE_ID, noteId);
        resultIntent.putExtra(UPDATE_NOTE, update);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancelUpdate(View view) {
        finish();
    }


}
