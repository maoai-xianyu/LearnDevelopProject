package com.mao.cn.learnDevelopProject.ui.features;

import com.mao.cn.learnDevelopProject.model.GirlModel;
import com.mao.cn.learnDevelopProject.ui.commons.BaseViewInferface;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020-03-03 14:48
 */
public interface IGirlImageView extends BaseViewInferface {

    void showGirlImage(List<GirlModel> girlModels);
}
