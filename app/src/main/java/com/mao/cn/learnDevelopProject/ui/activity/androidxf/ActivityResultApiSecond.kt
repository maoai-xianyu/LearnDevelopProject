package com.mao.cn.learnDevelopProject.ui.activity.androidxf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mao.cn.learnDevelopProject.R
import kotlinx.android.synthetic.main.include_header.*

/**
 *
 * @author zhangkun
 * @time 2020/12/10 7:50 PM
 * @Description
 */
class ActivityResultApiSecond : AppCompatActivity(R.layout.aty_result_api_second) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ib_header_back.visibility = View.VISIBLE
        tv_header_title.visibility = View.VISIBLE
        tv_header_title.text = "second"


        ib_header_back.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().putExtra("value", "I am back !"))
            finish()
        }
    }


}