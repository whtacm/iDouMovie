package com.robin.idoumovie.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haitao on 11/23/16.
 */
public class Subject implements Serializable{
    private Rating rating;//

    private String[] genres;//

    private List<Cast> casts;//

    private String title;

    private long collect_count;//

    private String  original_title;//

    private String subtype;//

    private List<Director> directors;//

    private String year;//

    private Images images;//

    private String alt;

    private String id;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(long collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public class Images {
        public String small;
        public String large;
        public String medium;
    }

    public class Rating {
        public int max;
        public float average;
        public int stars;
        public int min;
    }

    public class Cast {
        public String name;
    }

    public class Director {
        public String name;
    }
}
