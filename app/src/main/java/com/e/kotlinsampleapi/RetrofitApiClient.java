package com.e.kotlinsampleapi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by RR on 27-Dec-17.
 */

public class RetrofitApiClient {
//    www.champs21.com/wp-json/wp/v2/

    public static final String BASE_URL = "https://champs21.com/";
    //public static final String BASE_URL = "http://api.champs21.com/";
    //public static final String BASE_URL = " https://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitApiClient() {
    } // So that nobody can create an object with constructor

    public static synchronized Retrofit getClient() {
        // if (retrofit==null) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(URLHelper.BASE_URL + URLHelper.SUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        // }
        return retrofit;
    }


    public static synchronized Retrofit getClientWithId() {
        // if (retrofit==null) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(URLHelper.BASE_URL + URLHelper.SUB_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        // }
        return retrofit;
    }

    public static synchronized Retrofit getClientWithoutTime() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(URLHelper.BASE_URL + URLHelper.SUB_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiMovies getApiInterface() {
        return RetrofitApiClient.getClient().create(ApiMovies.class);
    }

    public static ApiMovies getApiInterfaceWithId() {
        return RetrofitApiClient.getClientWithId().create(ApiMovies.class);
    }

    public static ApiMovies getApiInterfaceWithoutTime() {
        return RetrofitApiClient.getClientWithoutTime().create(ApiMovies.class);
    }
}

