package com.robin.idoumovie.ui.fragment;


import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.robin.idoumovie.R;
import com.robin.idoumovie.adapter.GalleryAdapter;
import com.robin.idoumovie.adapter.TopMoviewAdapter;
import com.robin.idoumovie.entity.HttpResult;
import com.robin.idoumovie.entity.Subject;
import com.robin.idoumovie.service.HttpMethods;
import com.robin.idoumovie.ui.base.BaseFragment;
import com.robin.idoumovie.util.LogUtil;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 *
 */
@SuppressLint("ValidFragment")
public class RecommendFragment extends BaseFragment {
    private View gallery_header;

    Banner banner;

    @Bind(R.id.listview)
    ListView listview;

    @Bind(R.id.refresh)
    MaterialRefreshLayout refreshLayout;

    ImageView[] imgs;

    Subject[] headerSubjects;

    List<Subject> subjects = new LinkedList<>();

    int start;//

    int total;//

    TopMoviewAdapter adapter;

    public RecommendFragment(int resId) {
        super(resId);
    }

    @Override
    public void initView() {
        gallery_header = inflater.inflate(R.layout.gallery_header, null);

        banner = new Banner(gallery_header);

        imgs = new ImageView[3];
        headerSubjects = new Subject[3];
        for (int i = 0; i < imgs.length; i++) {
            ImageView img = new ImageView(getActivity());
            imgs[i] = img;
            img.setImageResource(R.mipmap.movie_poster);
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        //listview.addHeaderView(gallery_header);

        banner.vp.setAdapter(new GalleryAdapter(imgs, banner.dots));

        adapter = new TopMoviewAdapter(getActivity(), subjects);
        listview.setAdapter(adapter);

    }

    @Override
    public void initEvent() {
        banner.vp.setOnPageChangeListener(new GalleryChangeListener());

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getMovies();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                getMoreMovies();
            }
        });
    }

    private void getMoreMovies() {

        try {
            HttpMethods.getInstance().getTop(new Subscriber<HttpResult<List<Subject>>>() {
                @Override
                public void onCompleted() {
                    LogUtil.v("data completed");
                }

                @Override
                public void onError(Throwable e) {
                    refreshLayout.finishRefreshLoadMore();
                    LogUtil.v(e.getMessage());
                }

                @Override
                public void onNext(HttpResult<List<Subject>> listHttpResult) {

                    total = listHttpResult.getTotal();
                    start += listHttpResult.getCount();

                    for (Subject subject : listHttpResult.getSubjects()) {
                        LogUtil.v(subject.getTitle());
                    }
                    subjects.addAll(listHttpResult.getSubjects());
                    adapter.notifyDataSetChanged();
                    refreshLayout.finishRefreshLoadMore();
                }
            }, start, 10);
        } catch (Exception e) {
            LogUtil.v(e.getMessage());
        }

    }

    @Override
    public void initDate() {
        start = 0;

        //getMovies();
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh();
            }
        },500);
    }


    /**
     *
     */
    public void getMovies() {
        try {
            HttpMethods.getInstance().getTop(new Subscriber<HttpResult<List<Subject>>>() {
                @Override
                public void onCompleted() {
                    LogUtil.v("data completed");
                }

                @Override
                public void onError(Throwable e) {
                    refreshLayout.finishRefresh();
                    LogUtil.v(e.getMessage());
                }

                @Override
                public void onNext(HttpResult<List<Subject>> listHttpResult) {

                    subjects.clear();
                    total = listHttpResult.getTotal();
                    start = listHttpResult.getCount();

                    for (Subject subject : listHttpResult.getSubjects()) {
                        LogUtil.v(subject.getTitle());
                    }

                    subjects.addAll(listHttpResult.getSubjects());
                    adapter.notifyDataSetChanged();

                    refreshLayout.finishRefresh();
                }
            }, 0, 10);
        } catch (Exception e) {
            LogUtil.v(e.getMessage());
        }

    }

    private class GalleryChangeListener implements ViewPager.OnPageChangeListener {
        public GalleryChangeListener() {
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < banner.dots.size(); i++) {
                if (i == position) {
                    banner.dots.get(i).setBackgroundResource(R.drawable.dot_focused);
                } else {
                    banner.dots.get(i).setBackgroundResource(R.drawable.dot_normal);
                }
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class Banner {
        @Bind(R.id.vp)
        ViewPager vp;

        @Bind({R.id.v_dot0, R.id.v_dot1, R.id.v_dot2})
        List<View> dots;

        Banner(View view) {

            ButterKnife.bind(this, view);
        }
    }
}
