package com.mao.cn.learnDevelopProject.ui.presenterimp;

import android.os.Handler;

import com.mao.cn.learnDevelopProject.ui.commons.BasePresenterImp;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewGlideShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewGlideShowContentPresenter;
import com.mao.cn.learnDevelopProject.utils.download.DLTask;
import com.mao.cn.learnDevelopProject.utils.download.ProgressListener;
import com.mao.cn.learnDevelopProject.utils.download.TaskManager;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.io.File;

/**
 * author:  zhangkun .
 * date:    on 2018/11/1.
 */
public class EasyRecycleViewShowGlideContentPresenterImp extends BasePresenterImp implements EasyRecycleViewGlideShowContentPresenter {


    private IEasyRecycleViewGlideShowContent viewInterface;

    public EasyRecycleViewShowGlideContentPresenterImp(IEasyRecycleViewGlideShowContent viewInterface) {
        super();
        this.viewInterface = viewInterface;
    }

    @Override
    public void downloadImage(DLTask task) {

        new Handler().postDelayed(() -> {
            task.setListener(new PbListener(task));
            TaskManager.getPoolProxy().execute(task);
        }, 200);

    }

    class PbListener implements ProgressListener {
        private DLTask task;
        private long read = 0l;

        PbListener(DLTask task) {
            this.task = task;
        }

        @Override
        public void update(long readbytes, long totalsize) {
            viewInterface.setLoadingProgress((long) (read += readbytes), totalsize);
        }

        @Override
        public void success(DLTask dlTask) {
            String downSavePath = task.getSaveDir();
            viewInterface.downloadVideoSuccess(downSavePath);
            boolean exists = new File(downSavePath).exists();
            LogU.e("文件是否存在 " + exists);
        }

        @Override
        public void fail(DLTask dlTask) {
            viewInterface.downloadVideofailure();
        }
    }
}
