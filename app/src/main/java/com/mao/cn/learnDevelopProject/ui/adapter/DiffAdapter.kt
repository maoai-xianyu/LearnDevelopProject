package com.mao.cn.learnDevelopProject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mao.cn.learnDevelopProject.R
import com.mao.cn.learnDevelopProject.model.TestBean

/**
 *
 * @author zhangkun
 * @time 2020/12/11 3:21 PM
 * @Description
 */
class DiffAdapter(val context: Context, val list: MutableList<TestBean>) : RecyclerView.Adapter<DiffAdapter.DiffVH>() {

    private var mDatas: MutableList<TestBean> = list
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    fun setDatas(list: MutableList<TestBean>) {
        this.mDatas = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffVH {
        return DiffVH(mInflater.inflate(R.layout.item_diff, parent, false))
    }

    override fun onBindViewHolder(holder: DiffVH, position: Int) {
        val bean = mDatas[position]
        holder.tv1.text = bean.name
        holder.tv2.text = bean.desc
        holder.iv.setImageResource(bean.pic)
    }

    override fun getItemCount(): Int = mDatas.size

    class DiffVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv1: TextView = itemView.findViewById(R.id.tv1)
        val tv2: TextView = itemView.findViewById(R.id.tv2)
        val iv: ImageView = itemView.findViewById(R.id.iv)

    }
}