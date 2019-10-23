package com.e.kotlinsampleapi

import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 08/04/2018 at 17:56.
 */

interface ApiMovies {

    @GET("/filippella/Sample-API-Files/master/json/movies-api.json")
    fun getMovies() : Observable<MovieResponse>

    @FormUrlEncoded
    @POST(URLHelper.ADD_FCM)
    fun addFcm(@Field("fcm_id") fcm_id: String): Observable<FcmAdd.Result>
}