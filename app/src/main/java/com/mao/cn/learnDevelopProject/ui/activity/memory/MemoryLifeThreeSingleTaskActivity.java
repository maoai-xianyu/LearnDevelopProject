package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class MemoryLifeThreeSingleTaskActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogU.e("MemoryLifeThreeSingleTaskActivity onCreate");
        setContentView(R.layout.aty_memory_life_three);
    }

    public void lifeClickSone(View view){
        Intent intent = new Intent(this, MemoryLifeOneStandardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogU.e("MemoryLifeThreeSingleTaskActivity onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogU.e("MemoryLifeThreeSingleTaskActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogU.e("MemoryLifeThreeSingleTaskActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogU.e("MemoryLifeThreeSingleTaskActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.e("MemoryLifeThreeSingleTaskActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogU.e("MemoryLifeThreeSingleTaskActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryLifeThreeSingleTaskActivity onDestroy");
    }
}
