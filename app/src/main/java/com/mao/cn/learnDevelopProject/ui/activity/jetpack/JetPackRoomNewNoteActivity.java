package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

/**
 * zkangkun
 */

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mao.cn.learnDevelopProject.R;


public class JetPackRoomNewNoteActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    public static final String NOTE_ADDED = "new_note";
    private EditText etNewNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_jet_pack_room_new_note);

        etNewNote = (EditText) findViewById(R.id.etNewNote);

        Button button = (Button) findViewById(R.id.bAdd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultIntent = new Intent();
                if (TextUtils.isEmpty(etNewNote.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String note = etNewNote.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED, note);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();

            }
        });

    }


}
