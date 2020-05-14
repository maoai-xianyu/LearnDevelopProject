package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class MemoryLifeOneStandardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_memory_life_one);
    }


    @Override
    protected void onStart() {
        super.onStart();
        LogU.e("MemoryLifeOneStandardActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogU.e("MemoryLifeOneStandardActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogU.e("MemoryLifeOneStandardActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.e("MemoryLifeOneStandardActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogU.e("MemoryLifeOneStandardActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryLifeOneStandardActivity onDestroy");
    }

    public void lifeClickSingleTops(View view) {

        Intent intent = new Intent(this, MemoryLifeTwoSingleTopActivity.class);
        startActivity(intent);
    }

    public void lifeClickSingleTaskOne(View view) {

        Intent intent = new Intent(this, MemoryLifeThreeSingleTaskActivity.class);
        startActivity(intent);
    }

}
