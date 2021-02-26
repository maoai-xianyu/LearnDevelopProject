package com.mao.cn.learnDevelopProject.ui.activity.kotlin

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.databinding.AtyBannerBinding
import com.mao.cn.learnDevelopProject.utils.tools.LogU
import com.mao.cn.learnDevelopProject.widgets.banner.BannerAdapter
import com.mao.cn.learnDevelopProject.widgets.flow.XFlowLayout
import com.mao.cn.learnDevelopProject.widgets.flow.flowlayout.FlowAdapter
import java.util.*


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

        viewModel.tabLists.observe(this) {
            val bannerAdapter = TagAdapter(this)
            bannerAdapter.setContent(it)

            val itemCount = bannerAdapter.itemCount

            LogU.e("itemCount $itemCount")
            viewBinding.flowLayout.adapter = bannerAdapter
        }

        viewModel.loadTabList()


        viewBinding.btn.setOnClickListener {
            viewBinding.flowLayout.setMaxLine(-1)
            viewBinding.flowLayout.adapter.notifyDataChanged()


            viewBinding.lineFlow.setLineCount(Int.MAX_VALUE)
            viewBinding.lineFlow.flowAdapter.notifyDataChanged()
        }


        viewModel.tabTabs.observe(this) {
            val adapter = FlowAdapterShow(it as MutableList<String>, this)

            val count = adapter.count

            LogU.e(" count  $count")
            viewBinding.lineFlow.setAdapter(adapter)
        }

        viewModel.loadTabTabs()


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


    class TagAdapter(val activity: Activity) : XFlowLayout.Adapter() {
        private var content: List<String> = ArrayList()
        fun setContent(content: List<String>) {
            this.content = content
            notifyDataChanged()
        }

        override fun getItemCount(): Int {
            return content.size
        }

        override fun getItemViewByPos(pos: Int): View {
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(10, 10, 10, 10)
            if (pos % 5 == 0) {
                val imageView = ImageView(activity)
                imageView.setImageResource(R.mipmap.ic_launcher)
                imageView.layoutParams = layoutParams
                return imageView
            }
            if (pos % 7 == 0) {
                val layoutInflater: LayoutInflater = activity.layoutInflater
                val view: View = layoutInflater.inflate(R.layout.item_sample, null)
                view.layoutParams = layoutParams
                return view
            }
            val tv = TextView(activity)
            tv.setPadding(30, 5, 30, 5)
            tv.text = content[pos]
            tv.textSize = 20f
            tv.maxEms = 10
            tv.setSingleLine()
            tv.layoutParams = layoutParams
            tv.setBackgroundResource(R.drawable.bg_text_tag)
            if (pos % 3 == 0) {
                tv.textSize = 30f
                tv.setTextColor(Color.RED)
            } else if (pos % 3 == 1) {
                tv.setTextColor(Color.BLUE)
            }
            return tv
        }
    }

    class FlowAdapterShow(val banners: MutableList<String>, val activity: Activity) : FlowAdapter<String>(activity, banners) {
        override fun generateLayout(position: Int): Int {
            /*return if (position % 3 == 0) {
                R.layout.flow_item_heigher
            } else {
                R.layout.flow_item
            }*/

            return R.layout.flow_item
        }

        override fun getView(o: String?, parent: View) {
            val text = parent.findViewById<View>(R.id.flow_text) as TextView
            text.text = o
            text.setBackgroundDrawable(getBack())
            text.setOnClickListener { Toast.makeText(activity, o, Toast.LENGTH_SHORT).show() }
        }

        private fun getBack(): Drawable? {
            val drawable = GradientDrawable()
            drawable.cornerRadius = 8f
            drawable.setColor(Color.rgb(Random().nextInt(255), Random().nextInt(255), Random().nextInt(255)))
            return drawable
        }

    }

}