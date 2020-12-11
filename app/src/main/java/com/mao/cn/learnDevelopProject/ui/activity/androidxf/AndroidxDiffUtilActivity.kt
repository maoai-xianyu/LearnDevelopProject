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

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding.view.RxView
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.contants.ValueMaps
import com.mao.cn.learnDevelopProject.di.component.AppComponent
import com.mao.cn.learnDevelopProject.model.TestBean
import com.mao.cn.learnDevelopProject.ui.adapter.DiffAdapter
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity
import com.mao.cn.learnDevelopProject.ui.fragment.*
import com.mao.cn.learnDevelopProject.ui.fragment.androidxf.*
import com.mao.cn.learnDevelopProject.utils.tools.LogU
import kotlinx.android.synthetic.main.aty_androidx_diffuitl_rv.*
import kotlinx.android.synthetic.main.aty_androidx_fragment_vp.*
import kotlinx.android.synthetic.main.include_header.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * DESC   :
 * AUTHOR : androidx fragment 懒加载
 */
class AndroidxDiffUtilActivity : BaseActivity() {


    private var mDatas: MutableList<TestBean> = mutableListOf()
    private var mNewDatas: MutableList<TestBean> = mutableListOf()
    private val H_CODE_UPDATE = 1
    private val mAdapter: DiffAdapter by lazy {
        DiffAdapter(this, mDatas)
    }

    override fun getArgs(bundle: Bundle?) {}

    override fun setView(): Int {
        return R.layout.aty_androidx_diffuitl_rv
    }

    override fun initView() {
        ib_header_back.visibility = View.VISIBLE
        tv_header_title.visibility = View.VISIBLE
        tv_header_title.text = "RV DiffUtil"

        initData()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter


        btnRefresh.setOnClickListener {
            onRefresh()
        }

        btnRefresh1.setOnClickListener {
            onRefreshDiffUtil()
        }
    }

    override fun setListener() {
        RxView.clicks(ib_header_back).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND.toLong(),
                TimeUnit.MILLISECONDS)
                .subscribe({ aVoid: Void? -> finish() }) { throwable: Throwable -> LogU.e(throwable.message) }
    }


    override fun setupComponent(appComponent: AppComponent) {}

    private fun initData() {
        mDatas = mutableListOf()
        mDatas.add(TestBean("张旭童1", "Android", R.drawable.pic1))
        mDatas.add(TestBean("张旭童2", "Java", R.drawable.pic2))
        mDatas.add(TestBean("张旭童3", "背锅", R.drawable.pic3))
        mDatas.add(TestBean("张旭童4", "手撕产品", R.drawable.pic4))
        mDatas.add(TestBean("张旭童5", "手撕测试", R.drawable.pic5))
    }

    fun onRefresh() {
        try {

            LogU.d("mNewDatas " + mNewDatas.size)
            LogU.d("mDatas " + mDatas.size)
            mNewDatas.clear()
            for (bean in mDatas) {
                mNewDatas.add(bean)
            }
            mNewDatas[0].desc = "Android+"
            mNewDatas[0].pic = R.drawable.pic7 //模拟修改数据

            //别忘了将新数据给Adapter
            mDatas = mNewDatas
            mAdapter.setDatas(mDatas)
            mAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            LogU.e("e" + e.toString())
        }

    }


    fun onRefreshDiffUtil() {
        try {
            for (bean in mDatas) {
                mNewDatas.add(bean) //clone一遍旧数据 ，模拟刷新操作
            }
            mNewDatas.add(TestBean("赵子龙", "帅", R.drawable.pic6)) //模拟新增数据
            mNewDatas[0].desc = "Android+"
            mNewDatas[0].pic = R.drawable.pic7 //模拟修改数据
            val testBean: TestBean = mNewDatas[1] //模拟数据位移
            mNewDatas.remove(testBean)
            mNewDatas.add(testBean)

            val diffCallBack = DiffCallBack(mDatas, mNewDatas)
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallBack, true)
            diffResult.dispatchUpdatesTo(mAdapter)

            mDatas = mNewDatas
            mAdapter.setDatas(mDatas)



            //新宠
            //利用DiffUtil.calculateDiff()方法，传入一个规则DiffUtil.Callback对象，和是否检测移动item的 boolean变量，得到DiffUtil.DiffResult 的对象
            /*Thread { //放在子线程中计算DiffResult
                val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(DiffCallBack(mDatas, mNewDatas), true)
                val message: Message = mHandler.obtainMessage(H_CODE_UPDATE)
                message.obj = diffResult //obj存放DiffResult
                message.sendToTarget()
            }.start()*/
            //mAdapter.notifyDataSetChanged();//以前普通青年的我们只能这样，现在我们是文艺青年了，有新宠了
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
    }

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                H_CODE_UPDATE -> {
                    //取出Result
                    val diffResult: DiffUtil.DiffResult = msg.obj as DiffUtil.DiffResult
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(mAdapter)

                    //这种方法可以fix add 0 不滑动
                    /*diffResult.dispatchUpdatesTo(new ListUpdateCallback() {
                        @Override
                        public void onInserted(int position, int count) {
                            mAdapter.notifyItemRangeInserted(position, count);
                            if (position==0){
                                mRv.scrollToPosition(0);
                            }
                        }

                        @Override
                        public void onRemoved(int position, int count) {
                            mAdapter.notifyItemRangeRemoved(position, count);
                        }

                        @Override
                        public void onMoved(int fromPosition, int toPosition) {
                            mAdapter.notifyItemMoved(fromPosition, toPosition);
                        }

                        @Override
                        public void onChanged(int position, int count, Object payload) {
                            mAdapter.notifyItemRangeChanged(position, count, payload);
                        }
                    });*/

                    //别忘了将新数据给Adapter
                    mDatas = mNewDatas
                    mAdapter.setDatas(mDatas)
                }
            }
        }
    }
}