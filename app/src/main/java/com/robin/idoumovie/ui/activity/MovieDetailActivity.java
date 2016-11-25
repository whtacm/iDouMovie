package com.robin.idoumovie.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.robin.idoumovie.R;
import com.robin.idoumovie.ui.base.BaseActivity;
import com.robin.idoumovie.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        initAll();
    }


    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void initEvent() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        LogUtil.v("" + view.getId());
        switch (view.getId()) {
            case -1:
                finish();
                break;
            default:
                break;
        }
    }
}
