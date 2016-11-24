package com.robin.idoumovie.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.robin.idoumovie.R;
import com.robin.idoumovie.ui.base.BaseActivity;

import butterknife.Bind;

public class MovieDetailActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }


    @Override
    public void initView() {
        setSupportActionBar(toolbar);
    }
}
