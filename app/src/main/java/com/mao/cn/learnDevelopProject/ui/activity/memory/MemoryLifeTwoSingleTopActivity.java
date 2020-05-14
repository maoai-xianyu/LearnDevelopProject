package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class MemoryLifeTwoSingleTopActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogU.e("MemoryLifeTwoSingleTopActivity onCreate");
        setContentView(R.layout.aty_memory_life_two);
    }

    public void lifeClickS(View v){
        Intent intent = new Intent(this, MemoryLifeOneStandardActivity.class);
        startActivity(intent);
    }

    public void lifeClickSelf(View v){
        Intent intent = new Intent(this, MemoryLifeTwoSingleTopActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        LogU.e("MemoryLifeTwoSingleTopActivity onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogU.e("MemoryLifeTwoSingleTopActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogU.e("MemoryLifeTwoSingleTopActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogU.e("MemoryLifeTwoSingleTopActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.e("MemoryLifeTwoSingleTopActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogU.e("MemoryLifeTwoSingleTopActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryLifeTwoSingleTopActivity onDestroy");
    }
}
