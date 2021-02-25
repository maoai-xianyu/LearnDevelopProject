package com.mao.cn.learnDevelopProject.ui.activity.kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author zhangkun
 * @time 2021/2/25 11:37 AM
 * @Description
 */
class BannerViewModel : ViewModel() {

    val bannerData: MutableLiveData<MutableList<BannerBean>> = MutableLiveData()

    fun loadBanners() {

        val bannerBeanList = mutableListOf<BannerBean>()

        var bannerUrl = BannerUrl("https://img5.mtime.cn/mg/2021/02/25/105025.59595859_285X160X4.jpg")
        var bannerList = mutableListOf(bannerUrl)
        var banner = Banner("七宗罪", bannerList)
        var bannerBean = BannerBean(banner)
        bannerBeanList.add(bannerBean)


        bannerUrl = BannerUrl("https://img5.mtime.cn/mg/2021/02/25/095103.53899341_285X160X4.jpg")
        bannerList = mutableListOf(bannerUrl)
        banner = Banner("光晕", bannerList)
        bannerBean = BannerBean(banner)
        bannerBeanList.add(bannerBean)



        bannerUrl = BannerUrl("https://img5.mtime.cn/mg/2021/02/25/093221.97939788_285X160X4.jpg")
        bannerList = mutableListOf(bannerUrl)
        banner = Banner("蜘蛛侠3", bannerList)
        bannerBean = BannerBean(banner)
        bannerBeanList.add(bannerBean)



        bannerUrl = BannerUrl("https://img5.mtime.cn/mg/2021/02/24/105555.92001969_285X160X4.jpg")
        bannerList = mutableListOf(bannerUrl)
        banner = Banner("唐三", bannerList)
        bannerBean = BannerBean(banner)
        bannerBeanList.add(bannerBean)



        bannerUrl = BannerUrl("https://img5.mtime.cn/mg/2021/02/25/090609.11929856_285X160X4.jpg")
        bannerList = mutableListOf(bannerUrl)
        banner = Banner("碟中谍", bannerList)
        bannerBean = BannerBean(banner)
        bannerBeanList.add(bannerBean)


        bannerData.value = bannerBeanList

    }


    val tabLists: MutableLiveData<List<String>> = MutableLiveData()

    fun loadTabList() {

        val tabList = mutableListOf<String>()

        for (i in 0..2) {
            tabList.add("aaaaaaaaaaaaaa")
            tabList.add("bbbbbbbbbbbbb")
            tabList.add("cccccc")
            tabList.add("dddddddddd")
            tabList.add("ffffffffffffffffffff")
            tabList.add("ggggggggg")
        }
        tabLists.value = tabList

    }

    val tabTabs: MutableLiveData<List<String>> = MutableLiveData()

    fun loadTabTabs() {
        val arrays = arrayOf(
                "1.C",
                "2.Java",
                "3.Objective-C",
                "4.C++",
                "5.PHP",
                "6.C#",
                "7.(Visual) Basic",
                "8.Python",
                "9.Perl",
                "10.JavaScript",
                "11.Ruby",
                "12.Visual Basic .NET",
                "13.Transact-SQL",
                "14.Lisp",
                "15.Pascal",
                "16.Bash",
                "17.PL/SQL",
                "18.Delphi/Object Pascal",
                "19.Ada",
                "20.MATLAB", "1.C",
                "2.Java",
                "3.Objective-C",
                "4.C++",
                "5.PHP",
                "6.C#",
                "7.(Visual) Basic",
                "8.Python",
                "9.Perl",
                "10.JavaScript",
                "11.Ruby",
                "12.Visual Basic .NET",
        )

        tabTabs.value = arrays.toMutableList()


    }
}