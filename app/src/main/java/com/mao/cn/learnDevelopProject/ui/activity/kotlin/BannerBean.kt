package com.mao.cn.learnDevelopProject.ui.activity.kotlin

/**
 *
 * @author zhangkun
 * @time 2021/2/25 11:38 AM
 * @Description
 */
data class BannerBean(var banner_url: Banner)

data class Banner(var title: String, var url_list: List<BannerUrl>)

data class BannerUrl(var url: String)