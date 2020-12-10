package com.mao.cn.learnDevelopProject.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *
 * @author zhangkun
 * @time 2020/12/10 2:21 PM
 * @Description
 */
class CommonTabAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf()
    private val titleList: ArrayList<String> = arrayListOf()

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getPageTitle(position: Int): CharSequence = titleList[position]

    fun reloadData(fragments: List<Fragment>, titles: List<String>) {
        fragmentList.clear()
        fragmentList.addAll(fragments)
        titleList.clear()
        titleList.addAll(titles)
        notifyDataSetChanged()
    }

}