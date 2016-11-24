package com.robin.idoumovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robin.idoumovie.R;
import com.robin.idoumovie.entity.Subject;
import com.robin.idoumovie.ui.activity.MovieDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by haitao on 11/24/16.
 */
public class TopMoviewAdapter extends BaseAdapter {
    Context context;
    List<Subject> subjects;
    LayoutInflater inflater;

    public TopMoviewAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Subject getItem(int i) {
        return subjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.movie_item, null);
            vh = new ViewHolder(contentView);
            contentView.setTag(vh);
        } else {
            vh = (ViewHolder) contentView.getTag();
        }
        final Subject sub = subjects.get(i);

        Glide.with(context)
                .load(sub.getImages().small)
                .into(vh.posterImg);

        vh.titleTv.setText(sub.getTitle());
        vh.original_titleTv.setText(sub.getOriginal_title());
        vh.scoreTv.setText("" + sub.getRating().average);

        StringBuilder genres = new StringBuilder();
        for (String s : sub.getGenres()) {
            genres.append(s + " ");
        }
        vh.genresTv.setText(genres.toString());

        vh.yearTv.setText(sub.getYear());
        vh.directorTv.setText(sub.getDirectors().size() > 0 ? sub.getDirectors().get(0).name : "");
        vh.castsTv.setText(sub.getCasts().size() > 0 ? sub.getCasts().get(0).name : "");

        vh.sub_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
               // intent.putExtra("subject",sub);
                context.startActivity(intent);
            }
        });

        return contentView;
    }


    class ViewHolder {
        @Bind(R.id.sub_rl)
        RelativeLayout sub_rl;

        @Bind(R.id.poster)
        ImageView posterImg;

        @Bind(R.id.title_tv)
        TextView titleTv;

        @Bind(R.id.original_title_tv)
        TextView original_titleTv;

        @Bind(R.id.score_tv)
        TextView scoreTv;

        @Bind(R.id.genres_tv)
        TextView genresTv;

        @Bind(R.id.year_tv)
        TextView yearTv;

        @Bind(R.id.director_tv)
        TextView directorTv;

        @Bind(R.id.casts_tv)
        TextView castsTv;

        @Bind(R.id.detail_btn)
        Button detailBtn;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
