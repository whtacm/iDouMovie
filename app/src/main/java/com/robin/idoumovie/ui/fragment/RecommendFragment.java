package com.robin.idoumovie.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.robin.idoumovie.R;
import com.robin.idoumovie.adapter.TopMoviewAdapter;
import com.robin.idoumovie.entity.HttpResult;
import com.robin.idoumovie.entity.Subject;
import com.robin.idoumovie.service.HttpMethods;
import com.robin.idoumovie.ui.base.BaseFragment;
import com.robin.idoumovie.util.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 *
 */
@SuppressLint("ValidFragment")
public class RecommendFragment extends BaseFragment {

    @Bind(R.id.listview)
    ListView listview;

    @Bind(R.id.refresh)
    MaterialRefreshLayout refreshLayout;

    List<Subject> subjects = new LinkedList<>();

    int start;//

    int total;//

    TopMoviewAdapter adapter;

    View header;

    Banner banner;

    List<String> bannerTitles;
    List<String> bannerUrl;

    public RecommendFragment(int resId) {
        super(resId);
    }


    @Override
    public void initView() {
        bannerTitles = new LinkedList<>();
        bannerUrl = new LinkedList<>();

        header = inflater.inflate(R.layout.header_banner, listview, false);
        header.setVisibility(View.INVISIBLE);
        banner = (Banner) header.findViewById(R.id.banner);
        banner.
                setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerTitles(bannerTitles)
                .setImages(bannerUrl)
                .isAutoPlay(false)
                .start();

        listview.addHeaderView(header);
        adapter = new TopMoviewAdapter(getActivity(), subjects);
        listview.setAdapter(adapter);

    }

    @Override
    public void initEvent() {

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
        }, 500);
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
                    header.setVisibility(View.VISIBLE);


                    bannerUrl.clear();
                    bannerUrl.add("");
                    bannerUrl.add("");
                    bannerUrl.add("");

                    bannerTitles.clear();
                    bannerTitles.add("怎么加载其他图片资源-11");
                    bannerTitles.add("怎么加载其他图片资源-12");
                    bannerTitles.add("怎么加载其他图片资源-13");
                    banner.setImages(bannerUrl)
                            .setBannerTitles(bannerTitles)
                            .start();
                    adapter.notifyDataSetChanged();
                    refreshLayout.finishRefresh();
                }
            }, 0, 10);
        } catch (Exception e) {
            LogUtil.v(e.getMessage());
        }

    }


    class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
//            imageLoader.displayImage((String) path, imageView, options);
            imageView.setImageResource(R.mipmap.bg1);
        }

        @Override
        public ImageView createImageView(Context context) {
            ImageView simpleDraweeView = new ImageView(context);
            simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            return simpleDraweeView;
        }
    }
}
