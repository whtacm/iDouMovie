package com.robin.idoumovie.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by haitao on 11/24/16.
 */
@SuppressLint("ValidFragment")
public class BaseFragment extends Fragment {
    /**
     *
     */
    protected View root;

    private int resId;

    protected LayoutInflater inflater;

    public BaseFragment(int resId) {
        this.resId = resId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        root = inflater.inflate(resId, container, false);
        ButterKnife.bind(this, root);
        initAll();
        return root;
    }

    private void initAll() {
        initView();
        initEvent();
        initDate();
    }

    /**
     *
     */
    public void initEvent() {
    }

    /**
     *
     */
    public void initDate() {
    }

    /**
     *
     */
    public void initView() {
    }
}
