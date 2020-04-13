package com.mao.cn.learnDevelopProject.ui.activity.materialDesign;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class MDToolbarSearchActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_md_toolbar_search;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }*/


    }

    @Override
    public void setListener() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


    /**
     * 加载创建 menu 布局
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView actionView = (SearchView) item.getActionView();
        // 设置一处出来就显示搜索框
        actionView.setIconified(false);
        actionView.setIconifiedByDefault(false);

        actionView.setSubmitButtonEnabled(true);


        actionView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 提交文本
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        // SearchView 中查询按钮的点击事件
        actionView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        actionView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });


        return true;
    }

    /**
     * 点击menu的一些回掉
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
