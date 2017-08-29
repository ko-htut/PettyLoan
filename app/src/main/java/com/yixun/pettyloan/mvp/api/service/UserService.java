package com.yixun.pettyloan.mvp.api.service;
import com.yixun.pettyloan.rx.di.module.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 存放关于用户的一些api
 * Created by zongkaili on 2017/8/29.
 */
public interface UserService {

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);


}
