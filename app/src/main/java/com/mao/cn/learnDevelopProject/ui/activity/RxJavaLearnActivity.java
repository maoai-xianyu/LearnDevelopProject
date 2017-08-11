// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 18:35 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.component.DaggerRxJavaLearnComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.model.Student;
import com.mao.cn.learnDevelopProject.model.StudentCourse;
import com.mao.cn.learnDevelopProject.modules.RxJavaLearnModule;
import com.mao.cn.learnDevelopProject.ui.adapter.RxJavaLearnAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IRxJavaLearn;
import com.mao.cn.learnDevelopProject.ui.funcitonMethod.InitDataMethodFunc;
import com.mao.cn.learnDevelopProject.ui.funcitonMethod.RxJavaMethodFunc;
import com.mao.cn.learnDevelopProject.ui.presenter.RxJavaLearnPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ResourceU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.mao.cn.learnDevelopProject.R.id.tv_show;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class RxJavaLearnActivity extends BaseActivity implements IRxJavaLearn {

    @Inject
    RxJavaLearnPresenter presenter;

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.rvData)
    RecyclerView rvData;
    @BindView(tv_show)
    TextView tvShow;
    @BindView(R.id.sv_image)
    SimpleDraweeView svImage;
    @BindView(R.id.iv_show)
    ImageView ivShow;

    private RxJavaLearnAdapter adapter;
    private List<String> strings;

    private List<Student> students;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_rx_java_learn;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getString(R.string.header_rxjava));

        strings = new ArrayList<>();

        strings.add("rxjava_start");
        strings.add("rxjava_map");
        strings.add("rxjava_flatmap");
        strings.add("rxjava_lift");
        strings.add("rxjava_thread");
        strings.add("rxjava_threadM");

        LinearLayoutManager linearLayoutCourse = new LinearLayoutManager(context);
        linearLayoutCourse.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(linearLayoutCourse);
        adapter = new RxJavaLearnAdapter(this);
        adapter.addStringList(strings);
        rvData.setAdapter(adapter);
        initDataStudent();
    }

    private void initDataStudent() {
        students = InitDataMethodFunc.initStudentData();
    }


    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        adapter.addListener(str -> {
            switch (str) {
                case "rxjava_start":
                    rxjava_startFun();
                    break;
                case "rxjava_map":
                    rxjava_mapFun();
                    break;
                case "rxjava_flatmap":
                    rxjava_flatmapFun();
                    break;
                case "rxjava_lift":
                    rxjava_liftFun();
                    break;
                case "rxjava_thread":
                    rxjava_threadFun();
                    break;
                case "rxjava_threadM":
                    rxjava_threadMFun();
                    break;
                default:
                    break;
            }
        });
    }

    private void rxjava_threadMFun() {
        tvShow.setText(String.valueOf("rxjava 主线程 doOnSubscribe"));
        RxJavaMethodFunc.changeThreadMain(svImage);
    }

    private void rxjava_threadFun() {
        svImage.setVisibility(View.VISIBLE);
        tvShow.setText(String.valueOf("rxjava 多次切换 线程"));
        RxJavaMethodFunc.changeThreadMore();
    }

    private void rxjava_liftFun() {
        svImage.setVisibility(View.VISIBLE);
        tvShow.setText(String.valueOf("as 查看 lift log"));
        RxJavaMethodFunc.rxjava_lift();

    }

    private void rxjava_flatmapFun() {
        svImage.setVisibility(View.VISIBLE);
        tvShow.setText(String.valueOf("as 查看 flatmap log"));
        RxJavaMethodFunc.rxjava_flatmap();
    }

    private void rxjava_mapFun() {
        svImage.setVisibility(View.VISIBLE);
        RxJavaMethodFunc.rxjava_map(ivShow);
    }

    private void rxjava_startFun() {
        String[] list = ResourceU.getAssetsFileNames("images_cover");
        List<File> files = new ArrayList<>();
        if (list != null) {
            for (String aList : list) {
                files.add(new File(aList));
                LogU.i("测试名：  " + aList);
            }
        }

       /* new Thread() {
            @Override
            public void run() {
                super.run();
                for (File file : files) {
                    if (file.getName().endsWith(".jpg")) {
                        final Bitmap bitmap = ResourceU.getBitmap("images_cover/"+file.getName());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivShow.setImageBitmap(bitmap);
                            }
                        });
                    }
                }
            }
        }.start();*/

        Observable.from(files).filter(file -> StringU.endsWith(file.getName(), ".jpg"))
                .map(file -> ResourceU.getBitmap("images_cover/" + file.getName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(bitmap -> ivShow.setImageBitmap(bitmap));

        RxJavaMethodFunc.rxjavaSchedule();


        //
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<StudentCourse>>() {
                    @Override
                    public Observable<StudentCourse> call(Student student) {
                        return Observable.from(student.getStudentCourses());
                    }
                }).filter(studentCourse -> studentCourse.getCourse_price() > 3000)
                .compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(studentCourse -> LogU.i(studentCourse.toString()));


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerRxJavaLearnComponent.builder()
                .appComponent(appComponent)
                .rxJavaLearnModule(new RxJavaLearnModule(this))
                .build().inject(this);
    }
}
