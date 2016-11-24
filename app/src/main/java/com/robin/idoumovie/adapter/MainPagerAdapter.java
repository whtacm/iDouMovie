package com.robin.idoumovie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.robin.idoumovie.R;
import com.robin.idoumovie.ui.fragment.LatestMovieFragment;
import com.robin.idoumovie.ui.fragment.RecommendFragment;

/**
 * Created by haitao on 11/24/16.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RecommendFragment(R.layout.fragment_recommend);
            case 1:
            default:
                return new LatestMovieFragment(R.layout.fragment_latest_movie);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "推荐";
            case 1:
            default:
                return "最新";
        }
    }
}
