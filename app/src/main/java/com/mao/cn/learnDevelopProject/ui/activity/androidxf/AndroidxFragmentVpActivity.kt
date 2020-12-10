// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity.androidxf

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.jakewharton.rxbinding.view.RxView
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.contants.ValueMaps
import com.mao.cn.learnDevelopProject.di.component.AppComponent
import com.mao.cn.learnDevelopProject.ui.adapter.CommonPagerTabAdapter
import com.mao.cn.learnDevelopProject.ui.adapter.CommonPagerTabAndroidxAdapter
import com.mao.cn.learnDevelopProject.ui.adapter.CommonTabAdapter
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity
import com.mao.cn.learnDevelopProject.ui.features.IMain
import com.mao.cn.learnDevelopProject.ui.fragment.*
import com.mao.cn.learnDevelopProject.ui.fragment.FrameFragment.getInstance
import com.mao.cn.learnDevelopProject.ui.fragment.androidxf.*
import com.mao.cn.learnDevelopProject.utils.tools.LogU
import kotlinx.android.synthetic.main.aty_androidx_fragment_vp.*
import kotlinx.android.synthetic.main.include_header.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * DESC   :
 * AUTHOR : androidx fragment 懒加载
 */
class AndroidxFragmentVpActivity : BaseActivity() {

    override fun getArgs(bundle: Bundle?) {}

    override fun setView(): Int {
        return R.layout.aty_androidx_fragment_vp
    }

    override fun initView() {
        ib_header_back.visibility = View.VISIBLE
        ib_call_phone.visibility = View.VISIBLE
        tv_header_title.visibility = View.VISIBLE
        tv_header_title.text = "androidx 懒加载"
        initData()
    }

    override fun setListener() {
        RxView.clicks(ib_header_back).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND.toLong(), TimeUnit.MILLISECONDS).subscribe({ aVoid: Void? -> finish() }) { throwable: Throwable -> LogU.e(throwable.message) }
        RxView.clicks(ib_call_phone).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND.toLong(), TimeUnit.MILLISECONDS).subscribe({ aVoid: Void? -> callPhone() }) { throwable: Throwable -> LogU.e(throwable.message) }
    }

    //拨打电话
    private fun callPhone() {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + "15171476706")
            startActivity(intent)
        }
    }

    override fun setupComponent(appComponent: AppComponent) {}

    private fun initData() {
        val fragmentList: MutableList<Fragment> = ArrayList()
        fragmentList.add(AndroidxFragment1.getInstance("android 1"))
        fragmentList.add(AndroidxFragment2.getInstance("android 2"))
        fragmentList.add(AndroidxFragment3.getInstance("android 3"))
        fragmentList.add(AndroidxFragment4.getInstance("android 4"))
        fragmentList.add(AndroidxFragment5.getInstance("android 5"))
        val titles: MutableList<String> = ArrayList()
        titles.add("android 1")
        titles.add("android 2")
        titles.add("android 3")
        titles.add("android 4")
        titles.add("android 6")
        val tabAdapter = CommonTabAdapter(supportFragmentManager)
        tabAdapter.reloadData(fragmentList, titles)
        vpFragment.adapter = tabAdapter
        // 使用 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 同时会加载所有的fragment,但是只有当期显示的fragment才会执行onResume方法
        vpFragment.offscreenPageLimit = fragmentList.size
        tab.setupWithViewPager(vpFragment)
    }
}