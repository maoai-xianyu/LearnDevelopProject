package com.mao.cn.learnDevelopProject.ui.fragment.androidxf

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.ui.fragment.DefineView1Fragment
import com.mao.cn.learnDevelopProject.utils.tools.LogU
import kotlinx.android.synthetic.main.frg_androidx.*

/**
 *
 * @author zhangkun
 * @time 2020/12/10 11:25 AM
 * @Description
 */
class AndroidxFragment5 : Fragment() {

    companion object {

        fun getInstance(name: String): AndroidxFragment5 {
            val androidF = AndroidxFragment5()
            val args = Bundle()
            args.putString("name", name)
            androidF.arguments = args
            return androidF
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogU.d("AndroidxFragment5 onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogU.d("AndroidxFragment5 onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogU.d("AndroidxFragment5 onCreateView")
        return inflater.inflate(R.layout.frg_androidx, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            tvF.text = it.getString("name")
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogU.d("AndroidxFragment5 onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        LogU.d("AndroidxFragment5 onStart")
    }


    override fun onResume() {
        super.onResume()
        LogU.d("AndroidxFragment5 onResume")
    }

    override fun onPause() {
        super.onPause()
        LogU.d("AndroidxFragment5 onPause")
    }

    override fun onStop() {
        super.onStop()
        LogU.d("AndroidxFragment5 onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogU.d("AndroidxFragment5 onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogU.d("AndroidxFragment5 onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LogU.d("AndroidxFragment5 onDetach")
    }
}