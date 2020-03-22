package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class JetPackViewModelActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jet_pack_view_model);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final TextView mTextView = findViewById(R.id.tvNumber);
        final TextView mTextViewNext = findViewById(R.id.tvNumberNext);
        Button mButton = findViewById(R.id.bRandom);
        /*JetPackActivityViewModel generator = new JetPackActivityViewModel();
        String generatorNumber = generator.getNumber();*/
        // viewModel
        JetPackActivityViewModel model = ViewModelProviders.of(this).get(JetPackActivityViewModel.class);
        String generatorNumber = model.getNumber();
        mTextView.setText(generatorNumber);


        // viewmodel liveData
        JetPackActivityLiveData liveData = ViewModelProviders.of(this).get(JetPackActivityLiveData.class);
        final LiveData<String> liveDataNumber = liveData.getNumber();

        liveDataNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mTextViewNext.setText(s);
                LogU.i(TAG + " Data update in UI"+s);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveData.createNumber();
            }
        });


        LogU.i(TAG + " Random number set");

    }

}
