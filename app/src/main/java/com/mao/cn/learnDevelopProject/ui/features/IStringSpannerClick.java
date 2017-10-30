// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.features;

import com.mao.cn.learnDevelopProject.model.JinShanTranslate;
import com.mao.cn.learnDevelopProject.model.TransLateBaiDuDetail;
import com.mao.cn.learnDevelopProject.ui.commons.BaseViewInferface;

import java.util.List;

/**
* DESC   :
* AUTHOR : Xabad
*/
public interface IStringSpannerClick extends BaseViewInferface{
    void showWordTransLate(List<TransLateBaiDuDetail> trans_result);
    void showWordTransLateJinShan(JinShanTranslate trans_result);
}
