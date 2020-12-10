package com.mao.cn.learnDevelopProject.ui.activity.androidxf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.utils.tools.LogU
import kotlinx.android.synthetic.main.aty_result_api.*
import kotlinx.android.synthetic.main.include_header.*
import org.jetbrains.anko.toast

/**
 *
 * @author zhangkun
 * @time 2020/12/10 7:50 PM
 * @Description
 */
class ActivityResultApi : AppCompatActivity(R.layout.aty_result_api) {
    private val REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ib_header_back.visibility = View.VISIBLE
        tv_header_title.visibility = View.VISIBLE
        tv_header_title.text = "ActivityResultApi"


        ib_header_back.setOnClickListener { finish() }
        tvFirst.setOnClickListener { jump() }

        tvSecond.setOnClickListener {
            jumpSecond()

        }
    }

    // 方式二

    private val startActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
        LogU.d(" startActivity ActivityResultContracts ")
        if (result?.resultCode == Activity.RESULT_OK) {
            toast(result.data?.getStringExtra("value") ?: "")
        }
    }

    private fun jumpSecond() {
        startActivity.launch(Intent(this, ActivityResultApiSecond::class.java))
    }


    // 方式一
    private fun jump() {
        startActivityForResult(Intent(this, ActivityResultApiSecond::class.java), REQUEST_CODE)
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         LogU.d(" onActivityResult  ")
         if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
             toast(data?.getStringExtra("value") ?: "")
         }
     }
    // end
}