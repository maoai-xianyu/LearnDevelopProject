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


}