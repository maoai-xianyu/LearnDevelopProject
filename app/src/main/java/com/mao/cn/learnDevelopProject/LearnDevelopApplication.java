package com.mao.cn.learnDevelopProject;

import android.app.Activity;

import com.mao.cn.learnDevelopProject.common.CommApplication;
import com.mao.cn.learnDevelopProject.common.OttoManager;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerAppComponent;
import com.mao.cn.learnDevelopProject.contants.KeyMaps;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.domain.AnalyticsManager;
import com.mao.cn.learnDevelopProject.model.ServerInfo;
import com.mao.cn.learnDevelopProject.modules.AppModule;
import com.mao.cn.learnDevelopProject.utils.config.Config;
import com.mao.cn.learnDevelopProject.utils.tools.GsonU;
import com.mao.cn.learnDevelopProject.utils.tools.PathU;
import com.mao.cn.learnDevelopProject.utils.tools.PreferenceU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;
import com.mcxiaoke.packer.helper.PackerNg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by zhangkun on 2017/6/8.
 */

public class LearnDevelopApplication extends CommApplication {

    private static LearnDevelopApplication instance;
    private LinkedHashMap<String, Activity> activityMap = new LinkedHashMap<>();
    private static AppComponent component;
    public static String appChannel = ValueMaps.AppChannel.UNKNOWN;
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    @Inject
    AnalyticsManager analyticsManager;

    private static ServerInfo serverInfo;

    @Override
    protected void initApplication() {

    }

    @Override
    protected void beforeCreate() {

    }

    @Override
    protected void afterOnCreate() {
        instance = this;
        appChannel = PackerNg.getMarket(context(), "mvp");
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        getComponent().inject(this);
        OttoManager.register(this);
        analyticsManager.registerAppEnter();
        initServerInfo();
        PathU.getInstance().initDirs();

    }

    public static ServerInfo serverInfo() {
        if (serverInfo == null) {
            initServerInfo();
        }
        return serverInfo;
    }

    public static void initServerInfo() {
        serverInfo = new ServerInfo();
        // TODO: 2017/8/3 先这个样处理，每次有版本提升的时候，同时需要添加服务或者修改服务的时候需要修改   KeyMaps.ServerInfoConfig.SERVER_INFO_CONFIG
        PreferenceU.getInstance(context()).clear(KeyMaps.ServerInfoConfig.SERVER_INFO_CONFIG);
        try {
            String content = PreferenceU.getInstance(context()).getString(KeyMaps.ServerInfoConfig.SERVER_INFO_CONFIG);
            if (StringU.isEmpty(content)) {
                serverInfo.setServerName(Config.DEFAULT_SERVER_NAME);
                serverInfo.setServerHost(Config.DEFAULT_SERVER_HOST);
                serverInfo.setServerRecommendUrl(Config.DEFAULT_SERVER_APIURL_RECOMMEND);
                PreferenceU.getInstance(context()).saveString(KeyMaps.ServerInfoConfig.SERVER_INFO_CONFIG,GsonU.string(serverInfo));
            } else {
                serverInfo = GsonU.convert(content, ServerInfo.class);
            }
        } catch (Exception e) {
            serverInfo.setServerName(Config.DEFAULT_SERVER_NAME);
            serverInfo.setServerHost(Config.DEFAULT_SERVER_HOST);
            serverInfo.setServerRecommendUrl(Config.DEFAULT_SERVER_APIURL_RECOMMEND);
        }
        PreferenceU.getInstance(context()).saveString(KeyMaps.ServerInfoConfig.SERVER_INFO_CONFIG,GsonU.string(serverInfo));
    }


    public static LearnDevelopApplication getInstance() {
        return instance;
    }

    public static AppComponent getComponent() {
        return component;
    }

    public static void addAty(Activity aty) {
        if (getInstance() == null) return;
        if (aty == null) return;
        if (getInstance().activityMap == null) getInstance().activityMap = new LinkedHashMap<>();
        getInstance().activityMap.put(aty.getLocalClassName(), aty);
    }

    public static void removeAty(Activity aty) {
        if (getInstance() != null && aty != null && getInstance().activityMap != null && getInstance().activityMap.keySet().contains(aty.getLocalClassName())) {
            getInstance().activityMap.remove(aty.getLocalClassName());
        }
    }

    /**
     * 根据传入的Activity名，判断该Activity是否已经被激活
     */
    public static boolean containsAty(String atyLocalClassName) {
        return StringU.isNotEmpty(atyLocalClassName) && getInstance() != null && getInstance().activityMap != null && getInstance().activityMap.keySet().contains(atyLocalClassName);
    }

    public static Activity getTopActivity() {
        if (getInstance().activityMap == null || getInstance().activityMap.size() == 0) return null;
        ListIterator<Map.Entry<String, Activity>> i = new ArrayList<>(getInstance().activityMap.entrySet()).listIterator(getInstance().activityMap.size());
        if (i.hasPrevious()) {
            Map.Entry<String, Activity> entry = i.previous();
            return entry.getValue();
        }
        return null;
    }

    private static void finishAty(Activity aty, String[] excludes) {
        if (aty != null && !aty.isFinishing() && !StringU.endsWithAny(aty.getLocalClassName(), excludes)) {
            aty.finish();
        }
    }

    public void finishCourseAty() {
        for (String key : activityMap.keySet()) {
            Activity aty = (activityMap.get(key));
            if (aty != null && !aty.isFinishing()) {
                if (StringU.endsWith(aty.getLocalClassName(), ".MainActivity"))
                    aty.finish();
            }
        }
    }

    public static int getScreenWidth() {
        if (screenWidth == 0) {
            screenWidth = PreferenceU.getInstance(context()).getInt(KeyMaps.Screen.SCREEN_WIDTH);
        }
        return screenWidth;
    }

    public static int getScreenHeight() {
        if (screenHeight == 0) {
            screenHeight = PreferenceU.getInstance(context()).getInt(KeyMaps.Screen.SCREEN_HEIGHT);
        }
        return screenHeight;
    }

}
