package com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer;

/**
 * @author zhangkun
 * @time 2020-05-25 11:30
 * @Description
 */
public class DatabaseManager {


    private  static volatile DatabaseManager databaseManager;


    private ObservableT<ObserverUserModel,ObserverT<ObserverUserModel>> mTObservableT;

    private DatabaseManager() {

        mTObservableT = new ObservableT<>();
    }


    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = new DatabaseManager();
                }
            }
        }

        return databaseManager;
    }


    public  void  insert(ObserverUserModel observerUserModel){
        // 插入数据库

        // 通知观察者
        mTObservableT.update(observerUserModel);

    }


    public void register(ObserverT<ObserverUserModel> observer) {
        mTObservableT.register(observer);
    }

    public void unRegister(ObserverT<ObserverUserModel> observer) {
        mTObservableT.unRegister(observer);
    }

}
