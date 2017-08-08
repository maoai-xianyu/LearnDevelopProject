// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.features;

import com.mao.cn.learnDevelopProject.ui.commons.BaseViewInferface;
import com.mao.cn.learnDevelopProject.model.MovieDetail;

import java.util.List;

/**
* DESC   :
* AUTHOR : Xabad
*/
public interface IMain extends BaseViewInferface {

    void showTopMovie(List<MovieDetail> movieDetails,String platformTitle);

}
