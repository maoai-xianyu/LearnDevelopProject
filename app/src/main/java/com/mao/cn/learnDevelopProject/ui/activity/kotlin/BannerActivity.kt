package com.mao.cn.learnDevelopProject.ui.activity.kotlin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.databinding.AtyBannerBinding
import com.mao.cn.learnDevelopProject.widgets.banner.BannerAdapter

/**
 *
 * @author zhangkun
 * @time 2021/2/25 11:17 AM
 * @Description
 */
class BannerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding: AtyBannerBinding = DataBindingUtil.setContentView(this, R.layout.aty_banner)

        viewBinding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(BannerViewModel::class.java)

        viewModel.bannerData.observe(this) {

            val bannerAdapter = BannerAdapterShow(it, this)
            viewBinding.bannerView.setAdapter(bannerAdapter)
        }

        viewModel.loadBanners()


    }

    class BannerAdapterShow(val banners: MutableList<BannerBean>, val activity: Activity) : BannerAdapter() {
        override fun getView(position: Int, convertView: View?): View {
            var currentView: ImageView? = null
            if (convertView == null) {
                currentView = ImageView(activity)
            }
            if (currentView == null) {
                (convertView as ImageView).scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                currentView.scaleType = ImageView.ScaleType.CENTER_CROP
            }
            if (currentView == null) {
                Glide.with(activity).load(banners[position].banner_url.url_list[0].url).into(convertView as ImageView)
            } else {
                Glide.with(activity).load(banners[position].banner_url.url_list[0].url).into(currentView)
            }

            return currentView ?: convertView!!
        }

        override fun getCount(): Int = banners.size


        override fun getBannerDesc(position: Int): String = banners[position].banner_url.title
    }

}