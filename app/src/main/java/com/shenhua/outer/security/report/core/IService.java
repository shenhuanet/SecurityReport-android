package com.shenhua.outer.security.report.core;

import com.shenhua.outer.security.report.bean.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shenhua on 2017-09-27-0027.
 * Email shenhuanet@126.com
 */
public interface IService {

    @POST("app/login")
    Call<User> login(@Query("tel") String username, @Query("password") String password,
                     @Query("clientId") String clientId, @Query("sysType") int sysType);

}
