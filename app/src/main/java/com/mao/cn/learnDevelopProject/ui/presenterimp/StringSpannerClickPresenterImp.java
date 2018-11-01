// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 10/19/2017 15:57 下午
// +----------------------------------------------------------------------
// | Author:     xab(xy@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.presenterimp;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.callBack.StringCallback;
import com.mao.cn.learnDevelopProject.converter.RetrofitError;
import com.mao.cn.learnDevelopProject.di.interactors.StringSpannerClickInteractor;
import com.mao.cn.learnDevelopProject.model.JinShanTranslate;
import com.mao.cn.learnDevelopProject.model.TransLateBaiDu;
import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IStringSpannerClick;
import com.mao.cn.learnDevelopProject.ui.presenter.StringSpannerClickPresenter;
import com.mao.cn.learnDevelopProject.utils.network.NetworkUtils;
import com.mao.cn.learnDevelopProject.utils.tools.GsonU;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class StringSpannerClickPresenterImp extends BasePresenterImp implements StringSpannerClickPresenter {
    StringSpannerClickInteractor interactor;
    IStringSpannerClick viewInterface;

    public StringSpannerClickPresenterImp(IStringSpannerClick viewInterface, StringSpannerClickInteractor stringSpannerClickInteractor) {
        super();
        this.viewInterface = viewInterface;
        this.interactor = stringSpannerClickInteractor;
    }

    @Override
    public void getWordTranslateByBaiDu(String query, String from, String to) {
        if (!NetworkUtils.isConnected(context)) {
            viewInterface.onTip(context.getString(R.string.no_connect_net));
            return;
        }
        interactor.getWordTranslate(query, from, to, new StringCallback() {
            @Override
            public void success(String var1) {
                LogU.i(" 翻译 结果 " + var1);
                TransLateBaiDu result = GsonU.convert(var1, TransLateBaiDu.class);
                if (result != null && ListU.notEmpty(result.getTrans_result())) {
                    viewInterface.showWordTransLate(result.getTrans_result());
                } else {
                    viewInterface.onTip("无对应的词义");
                }
            }

            @Override
            public void failure(RetrofitError var1) {
                LogU.e("翻译失败");
                viewInterface.interError(var1);
                viewInterface.onTip("翻译失败");
            }
        });
    }

    @Override
    public void getWordTranslateByJinShan(String query) {
        if (!NetworkUtils.isConnected(context)) {
            viewInterface.onTip(context.getString(R.string.no_connect_net));
            return;
        }
        interactor.getWordTranslateByJinShan(query, new StringCallback() {
            @Override
            public void success(String var1) {
                LogU.i(" 翻译 结果 " + var1);
                JinShanTranslate result = GsonU.convert(var1, JinShanTranslate.class);
                if (result != null) {
                    viewInterface.showWordTransLateJinShan(result);
                } else {
                    viewInterface.onTip("无对应的词义");
                }
            }

            @Override
            public void failure(RetrofitError var1) {
                LogU.e("翻译失败");
                viewInterface.interError(var1);
                viewInterface.onTip("翻译失败");
            }
        });
    }
}
