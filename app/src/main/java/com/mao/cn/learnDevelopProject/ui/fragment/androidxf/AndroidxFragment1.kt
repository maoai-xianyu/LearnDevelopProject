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
class AndroidxFragment1 : Fragment() {

    companion object{
        fun getInstance(name: String): AndroidxFragment1 {

            val androidF = AndroidxFragment1()
            val args = Bundle()
            args.putString("name", name)
            androidF.arguments = args
            return androidF
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogU.d("AndroidxFragment1 onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogU.d("AndroidxFragment1 onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogU.d("AndroidxFragment1 onCreateView")
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
        LogU.d("AndroidxFragment1 onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        LogU.d("AndroidxFragment1 onStart")
    }


    override fun onResume() {
        super.onResume()
        LogU.d("AndroidxFragment1 onResume")
    }

    override fun onPause() {
        super.onPause()
        LogU.d("AndroidxFragment1 onPause")
    }

    override fun onStop() {
        super.onStop()
        LogU.d("AndroidxFragment1 onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogU.d("AndroidxFragment1 onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogU.d("AndroidxFragment1 onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LogU.d("AndroidxFragment1 onDetach")
    }

}