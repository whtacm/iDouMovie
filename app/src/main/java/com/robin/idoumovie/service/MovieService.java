package com.robin.idoumovie.service;



import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import com.robin.idoumovie.entity.HttpResult;
import com.robin.idoumovie.entity.Subject;
import rx.Observable;

/**
 * Created by haitao on 11/23/16.
 */
public interface MovieService {


    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
