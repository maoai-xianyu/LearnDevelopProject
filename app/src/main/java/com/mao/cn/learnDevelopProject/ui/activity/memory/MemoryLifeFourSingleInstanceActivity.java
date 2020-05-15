package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class MemoryLifeFourSingleInstanceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogU.e("MemoryLifeFourSingleInstanceActivity onCreate");
        setContentView(R.layout.aty_memory_life_four);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogU.e("MemoryLifeFourSingleInstanceActivity onNewIntent");
        super.onNewIntent(intent);
    }


    public void lifeClickStandard(View view){
        Intent intent = new Intent(this, MemoryLifeOneStandardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogU.e("MemoryLifeFourSingleInstanceActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogU.e("MemoryLifeFourSingleInstanceActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogU.e("MemoryLifeFourSingleInstanceActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.e("MemoryLifeFourSingleInstanceActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogU.e("MemoryLifeFourSingleInstanceActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryLifeFourSingleInstanceActivity onDestroy");
    }
}
