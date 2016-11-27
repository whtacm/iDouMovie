package com.robin.idoumovie.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
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
                .isAutoPlay(true)
                .setDelayTime(5000)
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
                    bannerUrl.add("http://img5.imgtn.bdimg.com/it/u=233600343,1016365940&fm=23&gp=0.jpg");
                    bannerUrl.add("http://img.loorin.com/data/attachment/forum/201506/28/093003vpdvoz4lcl5191k0.jpg");
                    bannerUrl.add("http://img0.ph.126.net/jEfaSloPBy3q2gVsG_0S-w==/6630259624652424975.jpg");
                    bannerUrl.add("http://pic36.photophoto.cn/20150730/0023006444962145_b.jpg");
                    banner.setImages(bannerUrl)
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
//            imageView.setImageResource(R.mipmap.bg1);

            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            ImageView simpleDraweeView = new ImageView(context);
//            simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            ViewGroup.LayoutParams lp = new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT);
//            simpleDraweeView.setLayoutParams(lp);

            return simpleDraweeView;
        }
    }
}
