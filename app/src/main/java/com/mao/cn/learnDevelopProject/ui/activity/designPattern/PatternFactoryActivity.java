package com.mao.cn.learnDevelopProject.ui.activity.designPattern;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar.DefaultNavigationBar;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factory.IOHandler;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factory.IOHandlerFactory;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factory.PreferenceUtils;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryMethod.IOHandlerFactoryM;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryMethod.IOHandlerM;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryMethod.IOHandlerMemoryFactory;
import com.mao.cn.learnDevelopProject.useDesign.d_03_factoryPattern.factoryMethod.IOHandlerPreferencesFactory;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * 工厂设计模式
 */
public class PatternFactoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_factory_design);

        ConstraintLayout root = findViewById(R.id.clRoot);

        DefaultNavigationBar defaultNavigationBar = new DefaultNavigationBar
                .Builder(this, root)
                .setText("返回")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .create();


        // 会有一些问题？
        // 1. 我们一般都会有一个清理缓存的功能，是不是以后需要清理?

        // 2. 清理的时候某些特定的内容不想清理，需要进行分开清理

        // 3. 可能以后为了保证性能，我们采取磁盘的存储，内存的存储 或者 采取数据库的存储

        // 4. 需要工厂设计模式写


        Button btGet = findViewById(R.id.btGet);

        TextView tvDesc = findViewById(R.id.tvDesc);
        TextView tvDesc1 = findViewById(R.id.tvDesc1);
        TextView tvDesc2 = findViewById(R.id.tvDesc2);
        TextView tvDesc3 = findViewById(R.id.tvDesc3);
        TextView tvDesc4 = findViewById(R.id.tvDesc4);


        // xml存储
        PreferenceUtils.getInstance().saveString("username", "zhankun");

        // 简单工厂模式
        IOHandler ioHandlerM = IOHandlerFactory.createIOHandler(IOHandlerFactory.IOType.MEMORY);
        ioHandlerM.save("age", "12个程序");

        IOHandler ioHandlerP = IOHandlerFactory.createIOHandler(IOHandlerFactory.IOType.PREFERENCES);
        ioHandlerP.save("city", "datong");

        // 工厂方法模式
        IOHandlerFactoryM ioHandlerMemoryFactory = new IOHandlerMemoryFactory();
        IOHandlerM ioHandlerMF = ioHandlerMemoryFactory.createIOHandlerM();
        ioHandlerMF.save("hh_test","ioHandlerMF");

        IOHandlerFactoryM ioHandlerPreferencesFactory = new IOHandlerPreferencesFactory();
        IOHandlerM ioHandlerMP = ioHandlerPreferencesFactory.createIOHandlerM();
        ioHandlerMP.save("hh_test_1","ioHandlerMP");



        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDesc.setText("单纯的 PreferenceUtils " + PreferenceUtils.getInstance().getString("username"));
                tvDesc1.setText("简单工厂类 Memory " + ioHandlerM.getString("age", "北京"));
                tvDesc2.setText("简单工厂类 PREFERENCES " + ioHandlerP.getString("city"));
                tvDesc3.setText("工厂方法 Memory " + ioHandlerMF.getString("age", "北京"));
                tvDesc4.setText("工厂方法 PREFERENCES " + ioHandlerMP.getString("city"));


            }
        });


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryActivity onDestroy");
    }
}
