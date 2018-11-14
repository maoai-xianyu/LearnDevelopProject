// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.ctetin.expandabletextviewlibrary.app.LinkType;
import com.ctetin.expandabletextviewlibrary.app.StatusType;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class ExpendTextViewContentActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.ep_01)
    ExpandableTextView expandableTextView;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_expend_text_view_show_content;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);

        /**
         *   正常的使用
         */
        //需要显示的内容
        String yourText = "我所认识的中国，强大、友好。@奥特曼 “一带一路”经济带带动了沿线国家的经济发展，促进我国与他国的友好往来和贸易发展，可谓“双赢”。http://www.baidu.com 自古以来，中国以和平、友好的面孔示人。汉武帝派张骞出使西域，开辟丝绸之路，增进与西域各国的友好往来。http://www.baidu.com 胡麻、胡豆、香料等食材也随之传入中国，汇集于中华美食。@RNG 漠漠古道，驼铃阵阵，这条路奠定了“一带一路”的基础，让世界认识了中国。";
        //将内容设置给控件
        expandableTextView.setContent(yourText);
        //xml中的属性也可以通过代码设置 比如
        expandableTextView.setNeedExpend(true);


    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        //还有很多。。。。
        //添加点击监听
        expandableTextView.setLinkClickListener((linkType, content, selfContent) -> {
            //根据类型去判断
            if (linkType.equals(LinkType.LINK_TYPE)) {
                Toast.makeText(ExpendTextViewContentActivity.this, "你点击了链接 内容是：" + content, Toast.LENGTH_SHORT).show();
            } else if (linkType.equals(LinkType.MENTION_TYPE)) {
                Toast.makeText(ExpendTextViewContentActivity.this, "你点击了@用户 内容是：" + content, Toast.LENGTH_SHORT).show();
            } else if (linkType.equals(LinkType.SELF)) {
                Toast.makeText(ExpendTextViewContentActivity.this, "你点击了自定义规则 内容是：" + content + " " + selfContent, Toast.LENGTH_SHORT).show();
            }
        });
        //添加展开和收回操作
        expandableTextView.setExpandOrContractClickListener(type -> {
            if (type.equals(StatusType.STATUS_CONTRACT)) {
                Toast.makeText(ExpendTextViewContentActivity.this, "收回操作", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ExpendTextViewContentActivity.this, "展开操作", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


}
