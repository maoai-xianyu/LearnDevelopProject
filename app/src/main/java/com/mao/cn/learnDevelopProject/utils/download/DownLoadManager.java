//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mao.cn.learnDevelopProject.utils.download;

import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class DownLoadManager {
    private static DownLoadManager sManager;
    private static OkHttpClient client;

    public DownLoadManager() {
    }

    public static DownLoadManager getInstance() {
        Class var0 = DownLoadManager.class;
        synchronized(DownLoadManager.class) {
            if (null == sManager) {
                Class var1 = DownLoadManager.class;
                synchronized(DownLoadManager.class) {
                    sManager = new DownLoadManager();
                }
            }
        }

        init();
        return sManager;
    }

    public OkHttpClient getClient() {
        return client;
    }

    private static void init() {
        if (client == null) {
            client = (new Builder()).connectTimeout(30000L, TimeUnit.MILLISECONDS).readTimeout(15000L, TimeUnit.MILLISECONDS).build();
        }

    }

    public void downloadTasks(List<DLTask> dlTasks) {
        if (dlTasks == null) {
            throw new NullPointerException("Tasks list is null");
        } else if (dlTasks.size() != 0) {
            for(int i = 0; i < dlTasks.size(); ++i) {
                DLTask dlTask = (DLTask)dlTasks.get(i);
                if (dlTask != null) {
                    TaskManager.getPoolProxy().execute(dlTask);
                }
            }

        }
    }
}
