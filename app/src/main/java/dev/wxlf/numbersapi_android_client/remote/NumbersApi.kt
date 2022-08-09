package dev.wxlf.numbersapi_android_client.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("/{number}/{type}")
    fun getFact(@Path("number") number: String, @Path("type") type: String): Single<String>

    @GET("/{month}/{number}/date")
    fun getDateFact(@Path("month") month: Int, @Path("number") number: Int,): Single<String>
}