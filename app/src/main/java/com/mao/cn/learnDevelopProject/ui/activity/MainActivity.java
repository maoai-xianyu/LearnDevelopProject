// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerMainComponent;
import com.mao.cn.learnDevelopProject.event.BusAction;
import com.mao.cn.learnDevelopProject.event.RefreshMainEvent;
import com.mao.cn.learnDevelopProject.event.RefreshMsgEvent;
import com.mao.cn.learnDevelopProject.modules.MainModule;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.ui.fragment.BooksFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.GamesFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.MainGuideFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.MoviesFragment;
import com.mao.cn.learnDevelopProject.ui.fragment.MusicFragment;
import com.mao.cn.learnDevelopProject.ui.presenter.MainPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.ashokvarma.bottomnavigation.ShapeBadgeItem.SHAPE_OVAL;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class MainActivity extends BaseActivity implements IMain {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.main_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private TextBadgeItem numberBadgeItem;
    private TextBadgeItem numberBadgeItemMovies;
    private ShapeBadgeItem shapeBadgeItem;
    private String[] mTitles = new String[]{"Home", "Books", "Music", "Movies & TV", "Games"};
    private List<BaseFragment> mFragmentList;
    private int currentIndex;
    private MoviesFragment moviesFragment;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_main;
    }

    @Override
    public void initView() {
        requestPermission();
        initBottomView();
        initFragment(0);

    }

    private void initBottomView() {
        numberBadgeItem = new TextBadgeItem();
        shapeBadgeItem = new ShapeBadgeItem();
        numberBadgeItemMovies = new TextBadgeItem();

        numberBadgeItem.setText("1") //显示的文本
                .setBorderWidth(1) //border宽度px
                .setBackgroundColorResource(R.color.defDialogColor) //背景色，资源文件获取
                .setBorderColorResource(R.color.defDialogColor) //border颜色，资源文件获取
                .setTextColorResource(R.color.c_FFFFFF) //文本颜色，资源文件获取
                .setAnimationDuration(30) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.END | Gravity.TOP)
                .show();

        numberBadgeItemMovies.setText("20") //显示的文本
                .setBorderWidth(1) //border宽度px
                .setBackgroundColorResource(R.color.defDialogColor) //背景色，资源文件获取
                .setBorderColorResource(R.color.defDialogColor) //border颜色，资源文件获取
                .setTextColorResource(R.color.c_FFFFFF) //文本颜色，资源文件获取
                .setAnimationDuration(30) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.END | Gravity.TOP)//位置，默认右上角
                .setHideOnSelect(true); //true：当选中状态时消失，非选中状态显示,moren false

        shapeBadgeItem.setShape(SHAPE_OVAL) //形状
                .setShapeColor(Color.BLUE) //颜色
                .setShapeColorResource(R.color.defDialogColor) //颜色，资源文件获取
                .setEdgeMarginInDp(this, 0) //距离Item的margin，dp
                .setEdgeMarginInPixels(20) //距离Item的margin，px
                .setSizeInDp(this, 10, 10) //宽高，dp
                .setSizeInPixels(5, 5) //宽高，px
                .setAnimationDuration(200) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.END) //位置，默认右上角
                .setHideOnSelect(true); //true：当选中状态时消失，非选中状态显示,moren false


        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, mTitles[0]))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, mTitles[1]).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, mTitles[2]))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, mTitles[3]).setBadgeItem(numberBadgeItemMovies))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, mTitles[4]).setBadgeItem(shapeBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise();
        //所有的设置需在调用该方法前完成``
    }

    private void initFragment(int position) {

        //tab 和 fragment 联动
        mFragmentList = new ArrayList<>();
        mFragmentList.add(MainGuideFragment.newInstance());
        mFragmentList.add(BooksFragment.newInstance());
        mFragmentList.add(MusicFragment.newInstance());
        if (moviesFragment ==null){
            moviesFragment = MoviesFragment.newInstance();
        }
        mFragmentList.add(moviesFragment);
        mFragmentList.add(GamesFragment.newInstance());
        showFragment(position);
    }


    @Override
    public void setListener() {

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (!checkActivityState()) return;
                //未选中->选中
                showFragment(position);
                switch (position) {
                    case 3:
                        numberBadgeItemMovies.setText("0");
                        numberBadgeItemMovies.setHideOnSelect(false);
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {
                //选中->未选中
            }

            @Override
            public void onTabReselected(int position) {
                if (!checkActivityState()) return;
                //选中->选中
                switch (position) {
                    case 1:
                        numberBadgeItem.setText("0");
                        numberBadgeItem.hide();
                        break;
                    default:
                        break;
                }
            }
        });


    }


    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build().inject(this);
    }


    private void showFragment(int position) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment fragment;
        fragment = mFragmentList.get(position);
        if (!fragment.isAdded()) {
            ft.add(R.id.fl_content, fragment);
        }
        ft.show(fragment);
        if (currentIndex != position) {
            ft.hide(mFragmentList.get(currentIndex));
            currentIndex = position;
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 获取需要操作日历的权限
     */
    private void requestPermission() {

        new RxPermissions(MainActivity.this)
                .requestEach(Manifest.permission.WRITE_CALENDAR,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CALL_PHONE)
                .subscribe(permission -> {
                    if (permission.granted) {

                    } else {
                        if (StringU.equals(permission.name, Manifest.permission.RECORD_AUDIO)) {
                            onTip("未授予录音权限,将会影响语音朗读");
                        }
                    }

                }, throwable -> LogU.e("异常"));
    }

    @Subscribe
    public void refreshRedPointer(RefreshMsgEvent event) {
        if (!checkActivityState()) return;
        mTitles[0] = "测试";

        /*bottomNavigationBar.clearAll();
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, mTitles[0]))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, mTitles[1]).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, mTitles[2]))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, mTitles[3]).setBadgeItem(numberBadgeItemMovies))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, mTitles[4]).setBadgeItem(shapeBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise();*/

        if (event.isShow()){
            numberBadgeItem.setText(event.getNumMsg());
            numberBadgeItem.show();
        }else {
            numberBadgeItem.hide();
        }

    }

    @Subscribe(
            tags = {
                    @Tag(BusAction.BUS_ACTION_CHANGE)
            }
    )
    public void refreshGamesMsgPointer(String event) {
        if (!checkActivityState()) return;
        numberBadgeItemMovies.setText(event);
        numberBadgeItemMovies.show();
    }


    @Subscribe
    public void refreshClickGamesMsg(String event) {
        if (!checkActivityState()) return;
        shapeBadgeItem.hide();
    }

    @Subscribe
    public void refreshMain(RefreshMainEvent event) {
        if (!checkActivityState()) return;
        bottomNavigationBar.clearAll();
        initBottomView();
        initFragment(3);
    }

}
