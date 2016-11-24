package com.robin.idoumovie.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by haitao on 11/24/16.
 */
public class GalleryAdapter extends PagerAdapter {
    ImageView[] imgs;
    List<View> dots;

    public GalleryAdapter(ImageView[] imgs,  List<View>  dots) {
        this.imgs = imgs;
        this.dots = dots;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgs[position]);
        return imgs[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs[position]);
    }
}
