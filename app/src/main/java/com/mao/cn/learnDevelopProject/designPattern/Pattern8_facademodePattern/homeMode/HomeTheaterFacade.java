package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode;

/**
 * @author zhangkun
 * @time 2020-02-20 11:10
 */
public class HomeTheaterFacade {
    private TheaterLights mTheaterLights;
    private Popcorn mPopcorn;
    private Stereo mStereo;
    private Projector mProjector;
    private Screen mScreen;
    private DVDPlayer mDVDPlayer;

    public HomeTheaterFacade() {
        mTheaterLights = TheaterLights.getInstance();
        mPopcorn = Popcorn.getInstance();
        mStereo = Stereo.getInstance();
        mProjector = Projector.getInstance();
        mScreen = Screen.getInstance();
        mDVDPlayer = DVDPlayer.getInstance();
    }

    public void ready() {
        mPopcorn.on();
        mPopcorn.pop();
        mScreen.down();
        mProjector.on();
        mStereo.on();

        mDVDPlayer.on();
        mDVDPlayer.play();
        mTheaterLights.dim(10);

    }

    public void end() {
        mPopcorn.off();
        mTheaterLights.bright();
        mScreen.up();
        mProjector.off();
        mStereo.off();

        mDVDPlayer.setDvd();
        mDVDPlayer.off();
    }

    public void play() {
        mDVDPlayer.play();
    }

    public void pause() {
        mDVDPlayer.pause();
    }
}
