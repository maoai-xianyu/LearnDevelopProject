package com.mao.cn.learnDevelopProject.di.modules;

import android.app.Application;

import com.mao.cn.learnDevelopProject.LearnDevelopApplication;
import com.mao.cn.learnDevelopProject.utils.tools.PreferenceU;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final LearnDevelopApplication app;

    public AppModule(LearnDevelopApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return LearnDevelopApplication.getInstance();
    }

    @Provides
    @Singleton
    PreferenceU providePreferenceU() {
        return PreferenceU.getInstance(LearnDevelopApplication.context());
    }

}