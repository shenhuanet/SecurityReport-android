package com.shenhua.outer.security.report.core;

import com.shenhua.outer.security.report.core.utils.Contanst;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class RetrofitHelper {

    private static RetrofitHelper sInstance = null;
    private Retrofit retrofit;

    public synchronized static RetrofitHelper get() {
        if (sInstance == null) {
            sInstance = new RetrofitHelper();
        }
        return sInstance;
    }

    private RetrofitHelper() {
        retrofit = new Retrofit.Builder().baseUrl(Contanst.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofitBaseUrl(String url) {
        this.retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
}
