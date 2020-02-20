package com.mao.cn.learnDevelopProject.designPattern.Pattern8_facademodePattern.homeMode;

/**
 * @author zhangkun
 * @time 2020-02-20 11:11
 */
public class Stereo {

    private static Stereo instance = null;
    private int volume = 5;

    private Stereo() {
    }

    public static Stereo getInstance() {
        if (instance == null) {
            instance = new Stereo();
        }
        return instance;
    }

    public void on() {
        System.out.println("Stereo on");
    }

    public void off() {
        System.out.println("Stereo off");
    }

    public void setVolume(int vol) {
        this.volume = vol;
        System.out.println("the volume of Stereo is set to " + volume);
    }

    public void addVolume() {
        if (volume < 11) {
            volume++;
            setVolume(volume);
        }
    }

    public void  subVolume() {
        if (volume >0){
            volume--;
            setVolume(volume);
        }
    }
}
