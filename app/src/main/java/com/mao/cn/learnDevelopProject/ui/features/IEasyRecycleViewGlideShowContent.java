// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.features;

import com.mao.cn.learnDevelopProject.ui.commons.BaseViewInferface;

/**
* DESC   :
* AUTHOR : Xabad
*/
public interface IEasyRecycleViewGlideShowContent extends BaseViewInferface{

    void setLoadingProgress(long l, long totalsize);

    void downloadVideoSuccess(String downSavePath);

    void downloadVideofailure();

}
