package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.trello.rxlifecycle.components.support.RxFragment;

public class LifeMe2Fragment extends RxFragment {

    public LifeMe2Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LifeMe2Fragment newInstance() {
        LifeMe2Fragment fragment = new LifeMe2Fragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogU.d("fragment2 onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogU.d("fragment2 onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogU.d("fragment2 onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frg_life_me2, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogU.d("fragment2 onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.d("fragment2 onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.d("fragment2 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.d("fragment2 onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.d("fragment2 onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogU.d("fragment2 onDestroyView");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogU.d("fragment2 onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogU.d("fragment2 onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogU.d("fragment2 onHiddenChanged");
    }
}
