package com.mao.cn.learnDevelopProject.ui.fragment.materialDesign;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.trello.rxlifecycle.components.support.RxFragment;

public class LifeMeFragment extends RxFragment {

    public LifeMeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LifeMeFragment newInstance() {
        LifeMeFragment fragment = new LifeMeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogU.d("fragment onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogU.d("fragment onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogU.d("fragment onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frg_life_me, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogU.d("fragment onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.d("fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.d("fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.d("fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.d("fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogU.d("fragment onDestroyView");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogU.d("fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogU.d("fragment onDetach");
    }

}
