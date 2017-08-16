package com.mao.cn.learnDevelopProject.ui.funcitonMethod;

import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mao.cn.learnDevelopProject.model.Student;
import com.mao.cn.learnDevelopProject.model.StudentCourse;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ResourceU;
import com.mao.cn.learnDevelopProject.utils.tools.StringU;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * author:  zhangkun .
 * date:    on 2017/8/11.
 */

public class RxJavaMethodFunc {


    //-----------------------------------------------------------------------------线程控制：Scheduler

    // 总是启用新线程，并在新线程执行操作
    public static <T> Observable.Transformer<T, T> newThreadSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    // 处理I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
    // 行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，
    // 因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。
    // 这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。
    // 不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
    public static <T> Observable.Transformer<T, T> applyIoSchedulersComputation() {
        return tObservable -> tObservable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * RxJava 多次切换线程
     * observeOn() 指定的是它之后的操作所在的线程。
     * 因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可
     */
    public static void changeThreadMore() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(integer -> integer + "数字 new线程")
                .observeOn(Schedulers.io())
                .map(s -> StringU.replace(s, "数字 new线程", "io 线程"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> LogU.i(" num " + s));
    }


    /**
     * doOnSubscribe()
     * 而与 Subscriber.onStart() 相对应的，有一个方法 Observable.doOnSubscribe() 。
     * 它和 Subscriber.onStart() 同样是在 subscribe() 调用后而且在事件发送前执行，但区别在于它可以指定线程。
     * 默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；
     * 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
     */
    public static void changeThreadMain(SimpleDraweeView svImage) {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> {
                    svImage.setVisibility(View.GONE);
                })    // 主线程
                .subscribeOn(AndroidSchedulers.mainThread())                // 指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> LogU.i(" num " + s));
    }


    /**
     * 线程的转换
     */
    public static void rxjavaSchedule() {
        // --------线程的转换  create
        // 观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                LogU.i("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String string) {
                LogU.i(string);
            }
        };


        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogU.i("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogU.i(s);
            }
        };

        // 被观察者
        Observable<String> objectObservable = Observable.unsafeCreate(subscriber1 -> {
            subscriber1.onNext("create hello");
            subscriber1.onNext("create world");
            subscriber1.onCompleted();
        });

        objectObservable.subscribeOn(Schedulers.newThread()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())  // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);

        //------------------- just
        Observable.just("just hello", "just world")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LogU::i);

        //------------------- from
        List<String> strings = new ArrayList<>();
        strings.add("from hello");
        strings.add("from world");
        Observable.from(strings)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LogU::i);

        String[] stringsArray = {"from array hello", "from array world"};
        Observable.from(stringsArray)
                .compose(newThreadSchedulers())
                .subscribe(LogU::i);
    }

    /**
     * map 是一对一的输出
     *
     * @param imageView
     */
    public static void rxjava_map(ImageView imageView) {
        //map(): 事件对象的直接变换
        Observable.just("images_cover/image_name_sign.png")
                .map(ResourceU::getBitmap)
                .compose(RxJavaMethodFunc.applyIoSchedulers())
                .subscribe(imageView::setImageBitmap);
    }

    /**
     * flatmap 处理一对多，数据准换
     * <p>
     * 1、将传入的事件对象装换成一个Observable对象；
     * 2、这是不会直接发送这个Observable, 而是将这个Observable激活让它自己开始发送事件；
     * 3、每一个创建出来的Observable发送的事件，都被汇入同一个Observable，这个Observable负责将这些事件统一交给Subscriber的回调方法。
     * <p>
     * 数据可能会交叉
     */
    public static void rxjava_flatmap() {
        Observable.from(InitDataMethodFunc.initStudentData())
                .flatMap(new Func1<Student, Observable<StudentCourse>>() {
                    @Override
                    public Observable<StudentCourse> call(Student student) {
                        return Observable.from(student.getStudentCourses());
                    }
                }).compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(studentCourse -> LogU.i(studentCourse.getCourse_name()));
    }

    /**
     * concatMap()解决了flatMap()的交叉问题，它能够把发射的值连续在一起
     */
    public static void rxjava_concatMap() {
        Observable.from(InitDataMethodFunc.initStudentData())
                .concatMap(new Func1<Student, Observable<StudentCourse>>() {
                    @Override
                    public Observable<StudentCourse> call(Student student) {
                        return Observable.from(student.getStudentCourses());
                    }
                }).compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(studentCourse -> LogU.i(studentCourse.getCourse_name()));
    }

    /**
     * flatMapIterable()它转化的多个Observable是使用Iterable作为源数据的。
     */
    public static void rxjava_FlatMapIterableMap() {
        Observable.from(InitDataMethodFunc.initStudentData())
                .flatMapIterable(new Func1<Student, Iterable<StudentCourse>>() {
                    @Override
                    public Iterable<StudentCourse> call(Student student) {
                        return student.getStudentCourses();
                    }
                }).compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(studentCourse -> LogU.i(studentCourse.getCourse_name()));
    }

    /**
     * 每当源Observable发射一个新的数据项（Observable）时，它将取消订阅并停止监视之前那个数据项产生的Observable，并开始监视当前发射的这一个
     * <p>
     * 在同一线程产生数据，依次打出
     */
    public static void rxjava_switchMap() {
        Observable.from(InitDataMethodFunc.initStudentData())
                .switchMap(new Func1<Student, Observable<StudentCourse>>() {
                    @Override
                    public Observable<StudentCourse> call(Student student) {
                        return Observable.from(student.getStudentCourses());
                    }
                }).compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(studentCourse -> LogU.i(studentCourse.getCourse_name()));
    }

    /**
     * 每当源Observable发射一个新的数据项（Observable）时，它将取消订阅并停止监视之前那个数据项产生的Observable，并开始监视当前发射的这一个
     * <p>
     * 在同一线程产生数据，依次打出
     */
    public static void rxjava_switchMapNew() {
        Observable.from(InitDataMethodFunc.initStudentData())
                .switchMap(new Func1<Student, Observable<StudentCourse>>() {
                    @Override
                    public Observable<StudentCourse> call(Student student) {
                        return Observable.from(student.getStudentCourses()).subscribeOn(Schedulers.newThread());
                    }
                }).compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(studentCourse -> LogU.i(studentCourse.getCourse_name()));
    }


    /**
     * scan()对一个序列的数据应用一个函数，并将这个函数的结果发射出去作为下个数据应用合格函数时的第一个参数使用。
     */
    public static void rxjava_scan() {
        Observable.just(1, 2, 3, 4, 5)
                .scan((integer, integer2) -> integer + integer2)
                .compose(RxJavaMethodFunc.newThreadSchedulers())
                .subscribe(integer -> LogU.i(" scan " + integer));
    }

    /**
     * GroupBy()将原始Observable发射的数据按照key来拆分成一些小的Observable，然后这些小Observable分别发射其所包含的的数据，和SQL中的groupBy类似。
     */
    public static void rxjava_GroupBy() {

        // GroupedObservable是一个特殊的Observable，它基于一个分组的key，在这个例子中的key就是 StudentCourse  名字
        Observable<GroupedObservable<String, StudentCourse>> groupedObservableObservable = Observable.from(InitDataMethodFunc.initStudentCourseData()).groupBy(studentCourse -> studentCourse.getCourse_name());

        Observable.concat(groupedObservableObservable).subscribe(studentCourse -> LogU.i(" StudentCourse 名字 " + studentCourse.getCourse_name() + "  班级描述： " + studentCourse.getCourse_desc()));

    }

    /**
     * lift 这些变换虽然功能各有不同，但实质上都是针对事件序列的处理和再发送。
     */
    public static void rxjava_lift() {
        Observable.just(1, 2, 3, 4).lift((Observable.Operator<String, Integer>) subscriber -> new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onNext(Integer integer) {
                subscriber.onNext("num +" + integer);
            }
        }).compose(RxJavaMethodFunc.newThreadSchedulers()).subscribe(s -> LogU.i(" lift " + s));
    }

}
