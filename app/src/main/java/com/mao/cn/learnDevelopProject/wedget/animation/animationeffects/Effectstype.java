package com.mao.cn.learnDevelopProject.wedget.animation.animationeffects;


import com.mao.cn.learnDevelopProject.wedget.animation.FadeIn;
import com.mao.cn.learnDevelopProject.wedget.animation.FadeOut;
import com.mao.cn.learnDevelopProject.wedget.animation.FlipH;
import com.mao.cn.learnDevelopProject.wedget.animation.FlipV;
import com.mao.cn.learnDevelopProject.wedget.animation.NewsPaper;
import com.mao.cn.learnDevelopProject.wedget.animation.ShakeY;
import com.mao.cn.learnDevelopProject.wedget.animation.SideFall;
import com.mao.cn.learnDevelopProject.wedget.animation.SlideLeft;
import com.mao.cn.learnDevelopProject.wedget.animation.SlideRight;
import com.mao.cn.learnDevelopProject.wedget.animation.SlideTop;

/**
 * Created by lee on 2014/7/30.
 */
//TODO
public enum Effectstype {

    Fadein(FadeIn.class),
    Fadeout(FadeOut.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideCenter(com.mao.cn.learnDevelopProject.wedget.animation.SlideCenter.class),
    SlideBottomIn(com.mao.cn.learnDevelopProject.wedget.animation.SlideBottomIn.class),
    SlideBottomOut(com.mao.cn.learnDevelopProject.wedget.animation.SlideBottomOut.class),
    SlideBottomForLevelUp(com.mao.cn.learnDevelopProject.wedget.animation.SlideBottomForLevelUp.class),
    Slideright(SlideRight.class),
    Fall(com.mao.cn.learnDevelopProject.wedget.animation.Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(com.mao.cn.learnDevelopProject.wedget.animation.RotateBottom.class),
    RotateLeft(com.mao.cn.learnDevelopProject.wedget.animation.RotateLeft.class),
    Slit(com.mao.cn.learnDevelopProject.wedget.animation.Slit.class),
    Shake(com.mao.cn.learnDevelopProject.wedget.animation.Shake.class),
    ShakeUpAndDown(ShakeY.class),
    Sidefill(SideFall.class),
    RotateH(com.mao.cn.learnDevelopProject.wedget.animation.RotateH.class),
    SlideBottomForCoinNum(com.mao.cn.learnDevelopProject.wedget.animation.SlideBottomForCoinNum.class),
    ScaleBig(com.mao.cn.learnDevelopProject.wedget.animation.ScaleBig.class),
    ImageViewScaleXY(com.mao.cn.learnDevelopProject.wedget.animation.ImageViewScaleXY.class);

    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
