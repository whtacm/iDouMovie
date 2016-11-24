package com.robin.idoumovie.ui.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by haitao on 11/24/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        initAll();
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
