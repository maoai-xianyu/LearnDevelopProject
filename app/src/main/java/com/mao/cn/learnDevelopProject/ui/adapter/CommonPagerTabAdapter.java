package com.mao.cn.learnDevelopProject.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mao.cn.learnDevelopProject.utils.tools.ListU;

import java.util.ArrayList;
import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2017/9/11.
 */

public class CommonPagerTabAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragmentList;

    private List<String> titleList;

    public CommonPagerTabAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<>();
        this.titleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (ListU.notEmpty(fragmentList)) {
            return fragmentList.get(position);
        }
        return null;
    }


    @Override
    public int getCount() {
        return ListU.isEmpty(titleList) ? 0 : titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void reloadData(List<Fragment> fragments, List<String> titles) {
        this.fragmentList.clear();
        this.fragmentList.addAll(fragments);
        this.titleList.clear();
        this.titleList.addAll(titles);
        notifyDataSetChanged();
    }
}
