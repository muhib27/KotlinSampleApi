package com.e.kotlinsampleapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class KotlinApiClient {


    companion object {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
            .build()

        private fun getRetrofit(Url:String):Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(Url)
                .client(okHttpClient)
                .build()
        }



        fun getApiData():Retrofit{
            val retrofitApi = getRetrofit(URLHelper.BASE_URL + URLHelper.SUB_URL)
            return retrofitApi
        }

        fun callApi():ApiMovies{
            val retrofitCall = getApiData()
            return retrofitCall.create(ApiMovies::class.java)
        }

    }

//    private var retrofit: Retrofit? = null
//
//    @Synchronized
//    fun getClientWithoutTime(): Retrofit {
//        if (retrofit == null) {
//
//            retrofit = Retrofit.Builder()
//                .baseUrl(URLHelper.BASE_URL + URLHelper.SUB_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//        }
//        return retrofit
//    }
//
//    @Synchronized
//    fun getClient(): Retrofit {
//        // if (retrofit==null) {
//        val okHttpClient = OkHttpClient.Builder()
//            .readTimeout(300, TimeUnit.SECONDS)
//            .connectTimeout(300, TimeUnit.SECONDS)
//            .build()
//        retrofit = Retrofit.Builder()
//            .baseUrl(URLHelper.BASE_URL + URLHelper.SUB_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(okHttpClient)
//            .build()
//        // }
//        return retrofit
//    }


    fun getApiInterfaceWithoutTime(): ApiMovies {
        return RetrofitApiClient.getClientWithoutTime().create(ApiMovies::class.java)
    }
}